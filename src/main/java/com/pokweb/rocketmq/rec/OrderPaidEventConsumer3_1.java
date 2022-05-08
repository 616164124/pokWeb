package com.pokweb.rocketmq.rec;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "batch", consumerGroup = "Test5")
public class OrderPaidEventConsumer3_1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("123");
        System.out.print("------- Test5消息内容OrderPaidEventConsumer received:"+ JSON.toJSONString(message));
    }
}