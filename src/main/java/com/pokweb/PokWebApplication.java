package com.pokweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@MapperScan(basePackages = { "com.pokweb.web.*.dao","com.pokweb.*.dao"})
public class PokWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PokWebApplication.class, args);

        System.out.println("启动完成");
    }

}
