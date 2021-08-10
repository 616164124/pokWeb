package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.service.impl.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
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
    @RequestMapping(value = "student_login", method = RequestMethod.POST)
    public  WebResponse loginIn(@RequestBody Map<String, Object> params) {
        System.out.println("====");
        System.out.println(loginService);


        return   loginService.login(params);
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
    @RequestMapping(value = "checkToken",method = RequestMethod.GET)
    public WebResponse checkToken(@RequestBody Map<String, Object> params){

        return loginService.checkToken(params);
    }
}