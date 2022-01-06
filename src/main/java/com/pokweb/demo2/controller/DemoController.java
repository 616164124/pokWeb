package com.pokweb.demo2.controller;

import com.pokweb.common.response.R;

import com.pokweb.common.response.WebResponse;
import com.pokweb.common.utils.RsaUtils;
import com.pokweb.demo2.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("demo2")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Resource
    public DemoService demoService;

    @Value("${public_key}")
    private String Public_key;

    @Value("${private_key}")
    private String Private_key;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @PostMapping("getdemo")
    public WebResponse getDemo(@RequestBody Map<String, String> params) {
        params.forEach((k, v) -> {
            System.out.println("k=" + k + "\tv=" + v);
        });
        logger.warn("1234");
//        R demo = demoService.getDemo();
        return WebResponse.ok();
    }

    @PostMapping("getdemo2")
    public WebResponse getDemo2(@RequestBody Map<String, String> params) {
        params.forEach((k, v) -> {
            System.out.println("k=" + k + "\tv=" + v);
        });

        try {
            Map<String, Object> map = RsaUtils.initKey();
            String publicKey = RsaUtils.getPublicKey(map);
            String privateKey = RsaUtils.getPrivateKey(map);
            System.out.println("公钥：" + publicKey);
            System.out.println("私钥：" + privateKey);
            String data = "再见孙悟空！";
            String encryptData = RsaUtils.encryptByPublicKey(data, publicKey);
            System.out.println("加密后：" + encryptData);
            String decryptData = RsaUtils.decryptByPrivateKey(encryptData, privateKey);
            System.out.println("解密后：" + decryptData);
            System.out.println("============");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return WebResponse.ok();
    }

    @PostMapping("email")
    public String test01() {
        String s = UUID.randomUUID().toString().split("-")[1];
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("邮件主题");
        message.setText("验证码：" + s);
        message.setTo("616164124@qq.com");
        message.setFrom("616164124@qq.com");
        javaMailSender.send(message);
        return "简单邮件发送成功！";
    }

}
