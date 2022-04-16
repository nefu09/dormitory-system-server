package cn.nlj.dormitorysystemserver.security;



import cn.nlj.dormitorysystemserver.common.Common;
import cn.nlj.dormitorysystemserver.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encryptComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
//        if (token == null) {
//            response.sendRedirect(request.getRemoteHost());
//        }
        //encryptComponent.decrypt(token);
        return true;
    }
}
