package com.pokweb.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //请求处理之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        //获取请求地址
        String requestURI = request.getRequestURI();
        if ("/pokweb/login".equals(requestURI)) {
//            return false;
        }else {
            String id =(String) request.getSession().getAttribute("id");
            String password =(String) request.getSession().getAttribute("password");
        }

//        Admin admin = (Admin) request.getSession().getAttribute("admin");
//        Student student = (Student) request.getSession().getAttribute("student");
//        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
//        if (null != admin || null != student || null != teacher) {
//            return true;
//        } else {
//            response.sendRedirect("/index");
//            return false;
//        }
        System.out.println("==preHandle===11");
//        response.sendRedirect("/acc");
        return false;
    }



}
