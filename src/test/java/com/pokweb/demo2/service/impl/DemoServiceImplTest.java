package com.pokweb.demo2.service.impl;

import com.pokweb.common.response.R;
import com.pokweb.demo2.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoServiceImplTest {
    @Resource
    private DemoService demoService;

    @Test
    public void test1(){
        R demo = demoService.getDemo();
        System.out.println(demo.toString());
    }
}