package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import com.pokweb.web.login.service.impl.LoginImpl;
import com.pokweb.web.register.dao.UserWorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pokweb")
public class LoginController {

    @Autowired
    private LoginImpl loginImpl;

    @Resource
    private LoginService loginService;

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


        return  loginService.login(params);
    }
}