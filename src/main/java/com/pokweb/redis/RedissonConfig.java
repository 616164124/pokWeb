package com.pokweb.redis;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 可以用yaml中来配置
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson configuration() {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
