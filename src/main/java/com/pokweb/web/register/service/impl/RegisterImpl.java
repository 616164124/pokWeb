package com.pokweb.web.register.service.impl;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.register.bo.UserTemporary;
import com.pokweb.web.register.dao.UserTemporaryDao;
import com.pokweb.web.register.service.RegisterService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RegisterImpl implements RegisterService {
    @Resource
    private UserTemporaryDao userTemporaryDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public WebResponse register(Map<String, Object> params) {

        UserTemporary userTemporary = new UserTemporary();
        //验证验证码
        String rediscode = redisTemplate.opsForValue().get(params.get("email").toString()).toString();
        if (!params.get("yzm").toString().equals(rediscode)) {
            return WebResponse.error("验证码不对重新输入", "验证码不对重新输入");
        }
        //注册
        if (params.get("password1").toString().equals(params.get("password2").toString())) {
            userTemporary.setName(params.get("name").toString());
            userTemporary.setEmail(params.get("email").toString());
            userTemporary.setIdentity("12");
            userTemporary.setPassword(params.get("password2").toString());
            int insert = userTemporaryDao.insert(userTemporary);
            return WebResponse.ok(insert);
        }
        return WebResponse.error();
    }

    //发送code，将code保存到redis
    public WebResponse sendEmailCode(String code, String email) {

        String rediscode = (String) redisTemplate.opsForValue().get(email);
        if (rediscode == "") {
            redisTemplate.opsForValue().set(email, code, 3, TimeUnit.MINUTES);
            //发送email
            sendEmail(code,email);

        }

        return WebResponse.ok("成功");
    }

    //发送邮件
    public void sendEmail(String code, String email) {

    }
}
