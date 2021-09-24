package com.pokweb.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 负责注册生效Interceptor
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("====addInterceptors======");
        //添加对用户未登录的拦截器，并添加排除项
       registry.addInterceptor(loginInterceptor);

    }

}
