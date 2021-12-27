package com.pokweb.demo2.controller;

import com.pokweb.common.response.R;
import com.pokweb.common.response.WebResponse;
import com.pokweb.common.utils.RsaUtils;
import com.pokweb.demo2.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("demo2")
public class DemoController {
    @Resource
    public DemoService demoService;

    @Value("${public_key}")
    private String Public_key;

    @Value("${private_key}")
    private String Private_key;

    @PostMapping("getdemo")
    public R getDemo(@RequestBody Map<String,String> params) {
        params.forEach((k,v)->{
            System.out.println("k="+k+"\tv="+v);
        });
        System.out.println(Public_key);
        R demo = demoService.getDemo();
        return R.ok();
    }
    @PostMapping("getdemo2")
    public WebResponse getDemo2(@RequestBody Map<String,String> params) {
        params.forEach((k,v)->{
            System.out.println("k="+k+"\tv="+v);
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

}
