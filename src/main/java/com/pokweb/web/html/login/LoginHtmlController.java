package com.pokweb.web.html.login;

import com.google.gson.Gson;
import com.pokweb.web.login.dao.UserStudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("/html")
public class LoginHtmlController {


    @RequestMapping("/login")
    public String login(){
        Gson gson = new Gson();
        String s = gson.toJson("jkfsfkshfk");
        return "login";
    }
}
