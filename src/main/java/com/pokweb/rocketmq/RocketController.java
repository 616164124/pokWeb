package com.pokweb.rocketmq;


import com.pokweb.common.base.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController
@Slf4j
public class RocketController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //延时消息
    @RequestMapping("TestAdd")
    public void TestAdd() {
        long l = System.currentTimeMillis();        //Test消息的主题 要和消费者的主题保持一致
        SendResult test = rocketMQTemplate.syncSend("Test", MessageBuilder.withPayload("user").build(), 2000, 3);//delayLevel 延时时间18个等级 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        long l1 = System.currentTimeMillis();
        System.out.println("=======上述代码执行时间为:=====" + (l1 - l));
        System.out.println("发送成功");
    }

    //一般消息
    @RequestMapping("Nor")
    public void pt() {
        User user = new User();
        rocketMQTemplate.syncSend("Nor", user, 1200);
    }

    //批量发送消息
    @RequestMapping("batch")
    public String batch() {
        long l = System.currentTimeMillis();
        User user = new User();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            user.setName(i + "");
            message.setBody(user.toString().getBytes(StandardCharsets.UTF_8));
            users.add(user);
        }

        rocketMQTemplate.syncSend("batch", users, 2000);
        log.info("上述代码执行时间===》{}", (System.currentTimeMillis() - l));
        return "12";


    }

    @PostMapping("sequentialMessage")
    public void sequentialMessage() {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.setQueueId(123);
        messageQueue.setTopic("sequentialMessage");
        rocketMQTemplate.syncSend("sequential", messageQueue);

    }

    /**
     * 接受方报错
     */
    @PostMapping("MessageException")
    public void sequentialMessage1() {
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.setQueueId(123);
        messageQueue.setTopic("sequentialMessage");
        SendResult sequential = rocketMQTemplate.syncSend("sequential:01", messageQueue);
        log.info("sequential==》" + sequential.toString());
    }
    /**
     * 事务消息
     */
    @PostMapping("transactionMsg01")
    public void transactionMsg01(){
        log.info("transactionMsg01===>");

        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction( "test","transactionMsg01:01", MessageBuilder.withPayload("mesg内容").setHeader("headername", "msg内容").build(), "");
        LocalTransactionState localTransactionState = transactionSendResult.getLocalTransactionState();
        LocalTransactionState localTransactionState1 = localTransactionState;
        if(localTransactionState1.equals(LocalTransactionState.COMMIT_MESSAGE)){
            log.info("另一事务发送成功COMMIT");
        }else if(localTransactionState1.equals(LocalTransactionState.ROLLBACK_MESSAGE)){
            log.info("本地事务回滚");
        }else if(localTransactionState1.equals(localTransactionState.UNKNOW)){
            log.info("不知事务是否成功");
        }
    }
}
