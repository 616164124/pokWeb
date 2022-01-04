package com.pokweb.web.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dev-api")
public class DoLoginController {
    private static final Logger logger = LoggerFactory.getLogger(DoLoginController.class);
//    captchaImage
    @GetMapping("captchaImage")
    public String    getCaptcha(){
        logger.info("captchaImage");
        return "";
    }

    @PostMapping("login")
    public String  login(){
        logger.info("captchaImage");
        return "";
    }

}
