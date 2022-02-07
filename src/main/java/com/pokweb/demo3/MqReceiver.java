package com.pokweb.demo3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Component
public class MqReceiver {

    @RabbitListener(queues = "hello")
    public void Receiver(String msg) {
        log.info("======>" + msg);
    }



}
