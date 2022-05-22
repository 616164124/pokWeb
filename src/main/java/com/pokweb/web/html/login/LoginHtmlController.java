package com.pokweb.web.html.login;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/html")
@Slf4j
public class LoginHtmlController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Gson gson = new Gson();
        String s = gson.toJson("jkfsfkshfk");

        return "web/test/login";
    }

    @RequestMapping(value = "/login/in", method = RequestMethod.POST)
    public void login_in() {
        return;
    }

    @RequestMapping(value = "/login/in2", method = RequestMethod.POST)
    public String test01(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject.get("name"));
        return "login";
    }
}
