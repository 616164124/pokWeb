package com.pokweb.interceptor;

import com.pokweb.common.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    //请求处理之前调用

    /**
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
