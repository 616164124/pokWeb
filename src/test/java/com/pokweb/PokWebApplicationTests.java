package com.pokweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
class PokWebApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println("888");
    }

    @Test
    void redisTest(){
        redisTemplate.opsForValue().set("877", "sfsf");
    }
}
