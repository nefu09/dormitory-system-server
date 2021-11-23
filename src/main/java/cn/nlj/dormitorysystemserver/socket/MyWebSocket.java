package cn.nlj.dormitorysystemserver.socket;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value="/MyWebSocket",configurator = SpringConfigurator.class)
public class MyWebSocket{
    //记录每个用户下多个终端的连接
    private static Map<String, Set<MyWebSocket>> userSocket = new HashMap<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(){
        System.out.println("socket 来了");
    }
}
