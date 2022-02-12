package com.pokweb.web.html.login;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/html")
public class LoginHtmlController {
    private static final Logger logger = LoggerFactory.getLogger(LoginHtmlController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Gson gson = new Gson();
        String s = gson.toJson("jkfsfkshfk");
        logger.info(s);
        return "web/test/login";
    }

    @RequestMapping(value = "/login/in", method = RequestMethod.POST)
    public void login_in() {
        logger.info("1231");
        return;
    }
}
