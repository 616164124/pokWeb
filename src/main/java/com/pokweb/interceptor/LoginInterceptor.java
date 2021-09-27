package com.pokweb.interceptor;

import com.pokweb.common.utill.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //请求处理之前调用
    @Override
    public boolean preHandle(@RequestBody HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        //获取请求地址
        String requestURI = request.getRequestURI();
        String token = request.getHeader("token");

        System.out.println("token====" + token);
        String method = request.getMethod();
        if ("/pokweb/login".equals(requestURI)) {
            return true;
        } else {
            //todo 验证token是否合法
            JwtUtil jwtUtil = new JwtUtil();
            //验证token是否合法
            if (jwtUtil.parserJwt(token).getResultCode() == "000000") {
                //验证token是否合法
                return true;
            } else {
                response.sendRedirect("http://localhost:8080/#/");
                return false;
            }

        }

    }

}
