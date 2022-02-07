package com.pokweb.rabbit.send;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
@Slf4j
@RestController
public class RabbitMqController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping("WorkQueuesSender")
    public void send1() {
//        Correlation correlation=new CorrelationData(new Date().getTime());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend("WorkQueuesSender", (Object) ("WorkQueuesSender====>" + new Date().getTime()), correlationData);
        }
    }

    @PostMapping("Exchange_PublishSubscribe")
    public void send2() {
        String s = UUID.randomUUID().toString();
        log.info("Exchange_PublishSubscribe==>send==>"+s);
        rabbitTemplate.convertAndSend("Exchange_PublishSubscribe", "", "\t"+s);
    }

}
