package com.pokweb.web.html.login;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
