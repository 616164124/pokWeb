package com.pokweb.web.register.service.impl;

import com.pokweb.common.response.WebResponse;
//import com.pokweb.web.register.bo.UserTemporary;
//import com.pokweb.web.register.dao.UserTemporaryDao;
import com.pokweb.web.register.dao.UserWorkDao;
import com.pokweb.web.register.service.RegisterService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RegisterImpl implements RegisterService {

    private static final String EMAIL_CODE = "emailcode:";

    //    @Resource
//    private UserTemporaryDao userTemporaryDao;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserWorkDao userWorkDao;


//    @Override
//    public WebResponse register(Map<String, Object> params) {
//
//        UserTemporary userTemporary = new UserTemporary();
//        //验证验证码
//        String rediscode = redisTemplate.opsForValue().get(params.get("email").toString()).toString();
//        if (!params.get("yzm").toString().equals(rediscode)) {
//            return WebResponse.error("验证码不对重新输入", "验证码不对重新输入");
//        }
//        //注册
//        if (params.get("password1").toString().equals(params.get("password2").toString())) {
//            userTemporary.setName(params.get("name").toString());
//            userTemporary.setEmail(params.get("email").toString());
//            userTemporary.setIdentity("12");
//            userTemporary.setPassword(params.get("password2").toString());
//            int insert = userTemporaryDao.insert(userTemporary);
//            return WebResponse.ok(insert);
//        }
//        return WebResponse.error();
//    }

    @Override
    public WebResponse register(Map<String, String> params) {
        String usernameCode = null;
        try {
            usernameCode = redisTemplate.opsForValue().get(EMAIL_CODE + params.get("username")).toString();
        } catch (Exception e) {
            return WebResponse.error("验证码过期","");
        }
        if (params.get("yzm").equals(usernameCode)) {
            int i = userWorkDao.selectByUserName(params.get("username"));
            if (i < 1) {
                String password = DigestUtils.md5DigestAsHex(params.get("password").getBytes());
                params.put("password",password);
                userWorkDao.insetAdmin(params);
                return WebResponse.ok(i);
            }else {
                return WebResponse.error("用户名已被注册","");
            }
        }
        return WebResponse.error("验证码验证不正确","");
    }

    //发送code，将code保存到redis
    public WebResponse sendEmailCode(String code, String email) {

        String rediscode = (String) redisTemplate.opsForValue().get(email);
        if (rediscode == "") {
            redisTemplate.opsForValue().set(email, code, 3, TimeUnit.MINUTES);
            //发送email
            sendEmail(code, email);

        }

        return WebResponse.ok("成功");
    }

    /**
     * redis保存5分钟
     *
     * @param username username
     * @param code     验证码
     */
    @Override
    public boolean saveRedisFor5(String username, String code) {
//        先判断redis是否已经存在相同的usernam 存在直接返回，不存在保存
        boolean s = redisTemplate.opsForValue().setIfAbsent(EMAIL_CODE + username, code, 3000l, TimeUnit.SECONDS);
        return s;
    }

    @Override
    public void clearRedis(String username) {
        redisTemplate.delete(EMAIL_CODE + username);
    }

    //发送邮件
    public void sendEmail(String code, String email) {

    }
}
