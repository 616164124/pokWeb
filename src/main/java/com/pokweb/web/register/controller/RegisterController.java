package com.pokweb.web.register.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.register.bo.UserWork;
import com.pokweb.web.register.dao.UserWorkDao;
import com.pokweb.web.register.service.RegisterService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("pokweb")
@CrossOrigin
@Slf4j
public class RegisterController {
    @Resource
    private UserStudentDao userStudentDao;
    @Resource
    private UserWorkDao userWorkDao;
    @Resource
    private RegisterService registerService;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${email}")
    private String email;
    @Value("${password}")
    private String password;

    /**
     * 学生不能注册为正式用户
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "student", method = RequestMethod.POST)
    public WebResponse registerStudent(Map params) {
        UserStudent userStudent = new UserStudent();
        userStudent.setName("学生王");
        int insert = userStudentDao.insert(userStudent);
        WebResponse chengong = new WebResponse("", "", insert);
        return chengong;
    }

    /**
     * TODO 通过布隆过滤器来判断是否已经有相同的用户名（username）
     *
     * @param params
     * @return WebResponse
     */
    @RequestMapping(value = "work", method = RequestMethod.POST)
    public WebResponse registerWork(@RequestBody Map<String, String> params) {
        UserWork userWork = new UserWork();
        //测试
        params.put("username", "yui@163.com");
        if (params.isEmpty()) {
            return new WebResponse("444", "参数为空", params);
        }
        // TODO: 2021/8/10  增加布隆过滤器
        if (userWorkDao.selectByUserName(params.get("username")) > 0) {

            return new WebResponse("444", params.get("username") + "\t该用户名已经注册", "");
        }
        if (params.get("password").length() < 6) {
            return new WebResponse("444", "密码长度要6位以上", null);
        }
        userWork.setUserName(params.get("username"));
        userWork.setName(params.get("name"));
        userWork.setPhone(params.get("phone"));
        userWork.setPhoneSos(params.get("phonesos"));
        userWork.setPassword(params.get("password"));
        userWork.setIdentity(params.get("identity"));

        int insert = userWorkDao.insert(userWork);
        WebResponse chengong = new WebResponse("200", "chengong", insert);
        return chengong;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public WebResponse registerTemporary(@RequestBody Map<String, Object> params) {
        Map data = (Map) params.get("data");
        WebResponse webResponse = registerService.register(data);
        return webResponse;
    }

    /**
     * 发送email的验证码
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "emailcode", method = RequestMethod.POST)
    public WebResponse sendCode(@RequestBody Map<String, Object> params) {
        params.forEach((k, v) -> {
            log.info("params==> k={} v={}", k, v);
        });

        String s = UUID.randomUUID().toString().split("-")[1];
//        redis保存5分钟 username  s
        boolean flag = registerService.saveRedisFor5(params.get("username").toString(), s);
        if (flag) {
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件设置
            message.setSubject("pokweb验证码");
//        message.setText("【阿里云】您正在登录验证，验证码"+s+"，切勿将验证码泄露于他人，本条验证码有效期15分钟。");
            message.setText("【pokweb】验证码：" + s + "，5分钟内有效。为了保证安全，请勿向他人泄露。谢谢您使用'pokweb'。");
            message.setTo(params.get("username").toString());
            message.setFrom(email);
            try {
                javaMailSender.send(message);
            } catch (MailException e) {
//                清除redis中的key
                registerService.clearRedis(params.get("username").toString());
                e.printStackTrace();
                return WebResponse.error("发送邮件出错", "");
            }
            return WebResponse.ok("简单邮件发送成功");
        } else {
            return WebResponse.error("已经发送，5分钟内不能重复发送", "");
        }


    }

}
