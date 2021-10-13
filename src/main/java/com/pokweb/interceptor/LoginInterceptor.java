package com.pokweb.interceptor;

import com.pokweb.common.utill.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //请求处理之前调用

    /**
     * 有权限应返回 true 放行,执行 controller
     * 没有权限返回 false,拒绝后续执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        //获取请求地址
        String requestURI = request.getRequestURI();
        String s = request.getHeader("tokens");
        String token = s;
        System.out.println("token====" + token);
        String method = request.getMethod();
        if ("/pokweb/login".equals(requestURI)) {
            return true;
        } else {
            // 验证token是否合法
            JwtUtil jwtUtil = new JwtUtil();
            //验证token是否合法
            if (jwtUtil.parserJwt(token).getResultCode() == "000000") {
                //验证token合法
                return true;
            } else {
//                response.sendRedirect("http://localhost:8080/#/");
                return true;
            }

        }

    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }

}
