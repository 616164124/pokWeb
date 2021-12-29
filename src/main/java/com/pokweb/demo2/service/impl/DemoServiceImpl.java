package com.pokweb.demo2.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pokweb.common.response.R;
import com.pokweb.demo2.dao.DemoDao;
import com.pokweb.demo2.service.DemoService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoDao demoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R getDemo() {

        Map<String, String> demo = demoDao.getDemo();
        setDemo();

        return R.ok().putResult(demo);
    }

    @Override
    public R setDemo(){
        demoDao.setDemo();
        int i=1/0;
        return R.ok();
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
