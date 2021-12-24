package com.pokweb.demo2.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

@SpringBootTest
class DemoDaoTest {

    @Resource
    private DemoDao demoDao;
    @Test
    public void tes01(){
        Map<String, String> demo = demoDao.getDemo();
    }



}