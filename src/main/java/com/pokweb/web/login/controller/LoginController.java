package com.pokweb.web.login.controller;

import com.pokweb.web.login.service.LoginService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("/pokweb")
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * TODO 验证码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginIn(Model model) {
        model.addAttribute("result", "后台返回index1");

        return "result";
    }
}