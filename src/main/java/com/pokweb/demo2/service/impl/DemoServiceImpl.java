package com.pokweb.demo2.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pokweb.common.response.R;
import com.pokweb.demo2.dao.DemoDao;
import com.pokweb.demo2.service.DemoService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoDao demoDao;

    @Override
    public R getDemo() {

        Map<String, String> demo = demoDao.getDemo();
        return R.ok().putResult(demo);
    }

    public static void main(String[] args) {

        System.out.println("json字符串转java代码");
        String jsonStr = "{\"password\":\"123456\",\"username\":\"张三\"}";
        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(jsonStr, Map.class);
        String username = map.get("username");
        String password = map.get("password");
        System.err.println("json--->java \n username="+username+"\t passwor="+password);
    }


}
