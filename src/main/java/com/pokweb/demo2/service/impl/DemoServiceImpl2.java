package com.pokweb.demo2.service.impl;

import com.pokweb.common.response.R;
import com.pokweb.demo2.dao.DemoDao;
import com.pokweb.demo2.service.DemoService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;

@Slf4j
public class DemoServiceImpl2 extends DemoServiceImpl implements DemoService2 {

    public void test(){
      log.info("DemoServiceImpl2===>test()==");
    }

    @Override
    public R getDemo2() {
        return null;
    }

    @Override
    public R setDemo2() {
        return null;
    }
}
