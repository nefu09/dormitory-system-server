package cn.nlj.dormitorysystemserver.socket;

import cn.nlj.dormitorysystemserver.entity.Student;
import cn.nlj.dormitorysystemserver.service.StudentService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{number}/{userName}")
@Component
@Slf4j
public class WebSocketServer {

    //记录每个用户下多个终端的连接
    private static Map<String, Set<WebSocketServer>> userSocket = new HashMap<>();
    private Session session;
    private String userId;

    private static StudentService studentService;
    @Autowired
    public  void setApplicationContext(StudentService studentService) {
        WebSocketServer.studentService= studentService;
    }
    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("number") String number, @PathParam("userName") String userName) throws JsonProcessingException {
        sessionMap.put(number, session);
        log.info("有新用户加入，username={}, 当前在线人数为：{}", userName, sessionMap.size());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        ObjectMapper objectMapper = new ObjectMapper();
        result.put("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", key);
            array.add(jsonObject);
        }
        //sendAllMessage(objectMapper.writeValueAsString(result));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("number") String number, @PathParam("username") String username) {
        sessionMap.remove(number);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("number") String number, @PathParam("userName") String username) {
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSONObject.parseObject(message);
        String toUsername = obj.getString("to"); // to表示发送给哪个用户，比如 admin
        String text = obj.getString("text"); // 发送的消息文本  hello
        // {"to": "admin", "text": "聊天文本"}
        JSONObject textJsonObject = JSONObject.parseObject(text);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", number);  // from 是 zhang
        jsonObject.put("text", textJsonObject.get("_value"));  // text 同上面的text
        if (toUsername.equals("all")) {
            sendAllMessage(jsonObject.toString());
        } else {
            Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
            if (toSession != null) {
                sendMessage(jsonObject.toString(), toSession);
                log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
            } else {
                log.info("发送失败，未找到用户username={}的session", toUsername);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给统一寝室楼的所有同学(宿舍群)
     */
    private void sendAllMessage(String message) {
        JSONObject obj = JSONObject.parseObject(message);
        String from = obj.getString("from");
        List<Student> students = studentService.getOneApartmentStudentsByStudentNumber(from);
        try {
            for (Student student : students) {
                if ( sessionMap.get(student.getStudentNumber())!=null){
                    sessionMap.get(student.getStudentNumber()).getBasicRemote().sendText(message);
                }
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}
