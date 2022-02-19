package com.pokweb.demo2.controller;

import com.google.gson.JsonObject;
import com.pokweb.common.response.R;

import com.pokweb.common.response.WebResponse;
import com.pokweb.common.utils.RsaUtils;
import com.pokweb.demo2.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("demo2")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Resource
    private DemoService demoService;
//    @Value("${public_key}")
//    private String Public_key;
//
//    @Value("${private_key}")
//    private String Private_key;


    @Resource
    private JavaMailSenderImpl javaMailSender;

    @PostMapping("getdemo")
    public R getDemo(@RequestBody Map<String, String> params) {
        params.forEach((k, v) -> {
            System.out.println("k=" + k + "\tv=" + v);
        });
        logger.info("1234");
//        R demo = demoService.getDemo();
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

        return R.ok();
    }

    @PostMapping("getdemo2")
    public WebResponse getDemo2(@RequestBody Map<String, String> params) {
        params.forEach((k, v) -> {
            System.out.println("k=" + k + "\tv=" + v);
        });

        try {
            R demo = demoService.getDemo();

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

    /**
     * 发送rabbitmq
     */
    @PostMapping("sendMq")
    public void sendMq() {
        demoService.send("hhhh");

    }


    @PostMapping("setDemo")
    public void setDemo() {
        demoService.setDemoException();
    }

    @GetMapping("setJson1")
    public Object setJson(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        logger.info("session id=>>{}", session.getId());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "String");

        return jsonObject.toString();
    }

    @GetMapping("setJson")
    public Object getJson(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        logger.info("session id=>>{}", session.getId());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "String");
        return jsonObject.toString();
    }

    /**
     * insert or update
     * 如果insert 失败 则执行 update
     */
    @PostMapping("insertorupdate")
    public void insertorupdate() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "1");
        hashMap.put("username", "uiere");
        hashMap.put("password", "fsj");
        demoService.insertorupdate(hashMap);
    }


    @GetMapping("getAdmin")
    public Object getAdmin() {


        return null;
    }
}

