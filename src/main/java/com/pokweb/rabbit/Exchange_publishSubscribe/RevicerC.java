package com.pokweb.rabbit.Exchange_publishSubscribe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "QueueC")
public class RevicerC {
    @RabbitHandler
    public void revicer_A(String msg){
        log.info("revicerC\t"+msg);
    }

}
