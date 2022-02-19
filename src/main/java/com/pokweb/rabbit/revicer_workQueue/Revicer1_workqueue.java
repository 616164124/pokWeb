package com.pokweb.rabbit.revicer_workQueue;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description:消息接收者
 * @RabbitListener bindings:绑定队列
 * @QueueBinding  value：绑定队列的名称
 *                  exchange：配置交换器
 * @Queue : value：配置队列名称
 *          autoDelete:是否是一个可删除的临时队列
 * @Exchange value:为交换器起个名称
 *           type:指定具体的交换器类型
 */


@Slf4j
@Component
@RabbitListener(queues = "WorkQueuesSender")
//@RabbitListener(
//        bindings = @QueueBinding(
//                value = @Queue(value = "WorkQueuesSender",autoDelete = "true"),
//                exchange = @Exchange(value = "", type = ExchangeTypes.DIRECT),
//                key = ""
//        )
//)
public class Revicer1_workqueue {
    @RabbitHandler
    public void revicer1(String msg, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("revicer1\t"+msg);
    }

}