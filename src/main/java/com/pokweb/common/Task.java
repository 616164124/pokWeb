package com.pokweb.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Task {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void TemporaryIntoRedis(){
//        redisTemplate;

    }

}
