package com.pokweb.rocketmq.rec;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "MessageException", consumerGroup = "Test2")
public class OrderPaidEventConsumer5 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        int i =1/0;
        System.out.print("------- 消息内容OrderPaidEventConsumer received:"+ JSON.toJSONString(message));
    }
}