package com.pokweb.web.login.service.impl;



import com.pokweb.common.response.WebResponse;
import com.pokweb.common.utill.JwtUtil;
import com.pokweb.common.utill.MapToUser;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
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

    private String key = "";

    @Override
    public WebResponse login(Map params) {
        if (params.isEmpty()) {
            return new WebResponse("888888", "账号或密码不能为空！！", "账号或密码不能为空！");
        }
        //todo 验证码

        WebResponse tokens = getTokens(params);
        return tokens;
    }

    @Override
    public WebResponse getMenu(String tokens) {
        String id = tokens.split("_")[0];
        String token = tokens.split("_")[1];
        List<Map> menu = userStudentDao.getMenu(id);
        return WebResponse.ok(menu);
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
    public WebResponse getTokens(Map params) {
        WebResponse webResponse = new WebResponse();
        HashMap<Object, Object> map = new HashMap<>();
        Map user;
        String token;
        if ("xs".equals(params.get("radio"))) {
            key = "token_userStudent_id:";
        } else if (params.get("radio") == "gzry") {
            key = "token_userWork_id:";
        } else if (params.get("radio") == "lsyh") {
            key = "token_userLSYH_id:";
        } else if (params.get("radio") == "admin") {
            key = "token_userAdmin_id:";
        }

        //查看学生表，工作人员表，临时表，admin表
        user = userStudentDao.selectUser((String) params.get("name"), (String) params.get("password"), params.get("radio").toString());
        JwtUtil jwtUtil = new JwtUtil();
        if (user == null) {
            webResponse.setResultMsg("密码或者用户名不正确！！！" + params.get("name") + "\t");
            webResponse.setResultCode("888888");
        } else {
            user.remove("password");
//            user.remove("id");
            //产生的token
            token = jwtUtil.JWTBuild(user);
            MapToUser mapToUser = new MapToUser();
            map.put("user",user);
            map.put("token", token);
            map.put("id", params.get("name").toString());
            redisTemplate.opsForValue().set("token_userStudent_id:" + params.get("name").toString(), token, 10, TimeUnit.MINUTES);
            webResponse.setResultObj(map);
            webResponse.setResultCode("200");
            webResponse.setResultMsg("");
        }
        return webResponse;
    }

    /**
     * 校验JWT值是否有效
     *
     * @param token
     * @return
     */
    //todo
    public boolean checkToken(String token) {


        return true;
    }

    /**
     * 获取JWT
     *
     * @return
     */
    public String getJWT() {

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
