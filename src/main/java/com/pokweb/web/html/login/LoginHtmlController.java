package com.pokweb.web.html.login;

import com.google.gson.Gson;
import com.pokweb.web.login.dao.UserStudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/html")
public class LoginHtmlController {


    @Resource
    private Gson gson;

    @RequestMapping("/login")
    public String login(){

        String s = gson.toJson("jkfsfkshfk");
        return s;
    }
}
