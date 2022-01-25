package com.pokweb.demo2.service.impl;

import com.google.gson.Gson;
import com.pokweb.common.response.R;
import com.pokweb.common.utils.mq.RibbitMqUtil;
import com.pokweb.demo2.dao.DemoDao;
import com.pokweb.demo2.service.DemoService;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class DemoServiceImpl implements DemoService {
//
    @Resource
    private DemoDao demoDao;
    @Resource
    private AmqpTemplate amqpTemplate;
// 构造器注入
//    private DemoDao demoDao;
//    private AmqpTemplate amqpTemplate;
//    public DemoServiceImpl(DemoDao demoDao,AmqpTemplate amqpTemplate) {
//        this.amqpTemplate=amqpTemplate;
//        this.demoDao=demoDao;
//    }

//    @Resource
//    private DemoDao demoDao;
//    //setter注入
//    private AmqpTemplate amqpTemplate;
//
//    @Autowired
//    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R getDemo() {

        Map<String, String> demo = demoDao.getDemo();
//        setDemo();

        return R.ok().putResult(demo);
    }

    @Override
    public R setDemo() {
//        demoDao.setDemo();
        int i = 1 / 0;
        return R.ok();
    }

    @Override
    public void send(String msg) {
        for (int i = 0; i<10; i++) {
            amqpTemplate.convertAndSend("hello", msg + "\t" + new Date().getTime());
        }
    }

    public static void main(String[] args) {

        System.out.println("json字符串转java代码");
        String jsonStr = "{\"password\":\"123456\",\"username\":\"张三\"}";
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(jsonStr, Map.class);
        String username = map.get("username");
        String password = map.get("password");
        System.err.println("json--->java \n username=" + username + "\t passwor=" + password);
    }

}
