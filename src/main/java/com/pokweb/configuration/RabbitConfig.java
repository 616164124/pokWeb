package com.pokweb.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);


    /**
     * new Queue(1,2,3,4) 可以有4个参数
     * 1.队列名
     * 2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
     * 3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
     * 4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean
    public Queue workQueue() {
        return new Queue("WorkQueuesSender", true);
    }

/*************广播***************************************/
    @Bean
    public FanoutExchange  publishSubscribeExchange() {
        //配置广播路由器
        logger.info("创建PublishSubscribeExchange成功");
        return new FanoutExchange("Exchange_PublishSubscribe");
    }

    @Bean
    public Binding bingQueueAToPublishSubscribeExchange() {
        logger.info("绑定PublishSubscribeQueueA到PublishSubscribeExchange成功");
        return BindingBuilder.bind(workQueue1()).to(publishSubscribeExchange());
    }

    @Bean
    public Binding bingQueueBToPublishSubscribeExchange() {
        logger.info("绑定PublishSubscribeQueueB到PublishSubscribeExchange成功");
        return BindingBuilder.bind(workQueue2()).to(publishSubscribeExchange());
    }

    @Bean
    public Binding bingQueueCToPublishSubscribeExchange() {
        logger.info("绑定PublishSubscribeQueueC到PublishSubscribeExchange成功");
        return BindingBuilder.bind(workQueue3()).to(publishSubscribeExchange());
    }


    @Bean
    public Queue workQueue1() {
        return new Queue("QueueA", true);
    }

    @Bean
    public Queue workQueue2() {
        return new Queue("QueueB", true);
    }

    @Bean
    public Queue workQueue3() {
        return new Queue("QueueC", true);
    }
/************广播****************************************/
//
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("hello");
//    }
//
//    @Bean
//    public Queue userQueue() {
//        return new Queue("user");
//    }
//
//    //===============以下是验证topic Exchange的队列==========
//    @Bean
//    public Queue queueMessage() {
//        return new Queue("topic.message");
//    }
//
//    @Bean
//    public Queue queueMessages() {
//        return new Queue("topic.messages");
//    }
//    //===============以上是验证topic Exchange的队列==========
//
//
//    //===============以下是验证Fanout Exchange的队列==========
//    @Bean
//    public Queue AMessage() {
//        return new Queue("fanout.A");
//    }
//
//    @Bean
//    public Queue BMessage() {
//        return new Queue("fanout.B");
//    }
//
//    @Bean
//    public Queue CMessage() {
//        return new Queue("fanout.C");
//    }
//    //===============以上是验证Fanout Exchange的队列==========
//
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("exchange");
//    }
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
//    }
//
//    /**
//     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//     * @param queueMessage
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }
//
//    /**
//     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//     * @param queueMessages
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }
//
//    @Bean
//    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(AMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(BMessage).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(CMessage).to(fanoutExchange);
//    }
}