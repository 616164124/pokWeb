package com.pokweb.rabbit.revicer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "WorkQueuesSender")
public class Revicer1_workqueue {
    @RabbitHandler
    public void revicer1(String msg){
      log.info("revicer1\t"+msg);
    }

}
