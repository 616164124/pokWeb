package com.pokweb.web.login.service.impl;


import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

@Service
public class LoginImpl implements LoginService {

    private ExecutorService POOL = Executors.newFixedThreadPool(16, new CustomizableThreadFactory("SbxxService-pool-"));
    @Resource
    private UserStudentDao userStudentDao;
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public WebResponse login(Map params) {
        System.out.println("=====123");
        int userStudent = userStudentDao.selectUserStudent(params.get("id").toString(), params.get("password").toString());
        if (userStudent == 0 || userStudent > 1) {
            return new WebResponse("400", "失败", "账号或密码不对");
        }
        WebResponse tokens = getTokens(params);
        return tokens;
    }

    @Override
    public WebResponse testThread(Map params) {
        List<Future<String>> futurelist = new ArrayList<Future<String>>();
        futurelist.add(POOL.submit(new T01()));
        futurelist.add(POOL.submit(new T02()));
        futurelist.add(POOL.submit(new T03()));
        for (Future<String> future : futurelist) {

            String s = null;
            try {
                s = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(s + "\timpl====jieshu");
        }

        return null;
    }

    /**
     * @param params
     * @return
     */
    @Override
    public WebResponse getTokens(Map params) {
        WebResponse webResponse = new WebResponse();
        int userStudent = userStudentDao.selectUserStudent((String) params.get("id"), (String) params.get("password"));
        if (userStudent == 0 || userStudent > 1) {
            //判断登录时这个id重复多次登录报错，将该id锁1个小时
            redisTemplate.opsForValue().append("userstudent_id" + params.get("id").toString(), "1");
            webResponse.setResultMsg("密码或者用户不对！！！" + params.get("id") + "\t");
            webResponse.setResultCode("400");
        } else {
            //新产生的token
            HashMap<Object, Object> map = new HashMap<>();
            String token = UUID.randomUUID().toString();
            map.put("token", token);
            map.put("id", params.get("id").toString());

            redisTemplate.opsForValue().set("token_userStudent_id:" + params.get("id").toString(), token, 10, TimeUnit.MINUTES);
            webResponse.setResultObj(map);
            webResponse.setResultCode("200");
            webResponse.setResultMsg("token生成成功");
        }
        return webResponse;
    }

    @Override
    public WebResponse checkToken(Map params) {
        String token = (String) params.get("token");
        String userid = (String) params.get("userid");
        String redisId = (String) redisTemplate.opsForValue().get(token);
        if (redisId == userid) {

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Instant.now().toEpochMilli());
    }

}

class T01 implements Callable {

    @Override
    public Object call() throws Exception {
        Thread.sleep(4000);
        System.out.println("T01==============");
        return "T01";
    }
}

class T02 implements Callable {

    @Override
    public Object call() throws Exception {
        Thread.sleep(4000);
        System.out.println("T02==============");

        return "T02";
    }
}

class T03 implements Callable {

    @Override
    public Object call() throws Exception {
        Thread.sleep(4000);
        System.out.println("T03==============");

        return "T03";
    }
}
