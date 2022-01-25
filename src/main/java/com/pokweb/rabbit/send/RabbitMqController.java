package com.pokweb.rabbit.send;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

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
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("Exchange_PublishSubscribe", "", "\t"+UUID.randomUUID().toString());
    }

}
