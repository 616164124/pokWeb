package com.pokweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pokweb.*.*.dao")
public class PokWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokWebApplication.class, args);
    }

}
