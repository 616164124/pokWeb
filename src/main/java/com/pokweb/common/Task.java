package com.pokweb.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class Task {

    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void TemporaryIntoRedis() {
        log.info("========");
        //        redisTemplate;

    }

}
