package com.pokweb.rocketmq.rec;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import sun.net.idn.Punycode;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "producer01",topic = "rocket")
public class RecRocket implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
    log.info("你好！"+message);
    }
}
