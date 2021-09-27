package com.pokweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.pokweb.*.*.dao")
public class PokWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PokWebApplication.class, args);
        //获取application.properties中的值
        System.out.println(run.getEnvironment().getProperty("jwtsalt"));

    }

}
