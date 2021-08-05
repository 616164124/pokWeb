package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import com.pokweb.web.login.service.impl.LoginImpl;
import com.pokweb.web.register.dao.UserWorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/pokweb")
public class LoginController {

    @Resource
    private LoginImpl loginImpl;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private LoginService loginService;

    /**
     * TODO 验证码
     *
     * @param params
     * @return
     */
    @Transactional
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public  WebResponse loginIn(@RequestBody Map<String, Object> params) {
        params.put("1",1);
        params.put("2", 3);
        redisTemplate.opsForValue().set("1", params,12, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("1", "123");

        return  loginService.login(params);
    }
    @RequestMapping(value="threadpool",method = RequestMethod.GET)
    public void testThread(){


    }

}