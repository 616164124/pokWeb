package com.pokweb.rocketmq;

import com.pokweb.common.base.User;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Rocketmq01send {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("rocket01")
    public void rocket01(){
        User user = new User();
        user.setName("hua");
        rocketMQTemplate.syncSend("rocket:01", user.toString(), 1000l);
    }

}
