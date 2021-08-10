package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.service.impl.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/pokweb")
public class LoginController {

    @Autowired
    private LoginImpl loginService;

    /**
     * TODO 验证码
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public  WebResponse loginIn(@RequestBody Map<String, Object> params) {
        System.out.println("====");
        System.out.println(loginService);
        for(int i=0 ;i<10;i++){
            loginService.testThread(params);
        }
        System.out.println("jieshu");
//        loginService.login(params);
        return  null;
    }

    /**
     * gettoken
     * @param params
     * @return
     */
    @RequestMapping(value="token",method = RequestMethod.GET)
    public WebResponse getToken(@RequestBody Map<String, Object> params){
        return loginService.getTokens(params);
    }
}