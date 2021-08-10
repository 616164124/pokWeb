package com.pokweb.web.login.service.impl;


import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class LoginImpl implements LoginService {

    private ExecutorService POOL = Executors.newFixedThreadPool(16, new CustomizableThreadFactory("SbxxService-pool-"));
    @Resource
    private UserStudentDao userStudentDao;


    @Override
    public WebResponse login(Map params) {
        System.out.println("=====123");
        if ((int) params.get("1") == 1) {
            System.out.println("====");
        } else {
            System.out.println("++++++++");
        }


        Map paramsMap = (Map) params;
        UserStudent userStudent1 = userStudentDao.selectUserStudent(paramsMap.get("id").toString(), paramsMap.get("password").toString());
        return new WebResponse("2002", "sjflks", "");
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
            System.out.println(s+"\timpl====jieshu");
        }

        return null;
    }

    @Override
    public WebResponse getTokens(Map params) {
        UserStudent userStudent = userStudentDao.selectUserStudent((String) params.get("username"),(String) params.get("password"));
        WebResponse webResponse = new WebResponse();
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
