package com.pokweb.rabbit.revicer_workQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "WorkQueuesSender")
public class Revicer2_workqueue {
    @RabbitHandler
    public void revicer2(String msg) {
        log.info("revicer2\t"+msg);
    }

}
