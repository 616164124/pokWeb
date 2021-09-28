package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/pokweb")
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * TODO 验证码
     *
     * @param params 为{data：{name:1234,password:12344}}
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public WebResponse loginIn(@RequestBody Map<String, Object> params) {
        params = (Map) params.get("data");
        System.out.println(loginService);
        return loginService.login(params);
    }

    @RequestMapping(value = "getMenu", method = RequestMethod.POST)
    public WebResponse getMenu(@RequestBody Map<String, Object> params) {
        WebResponse menu = new WebResponse();
        Map data = (Map) params.get("data");
        String id = data.get("id").toString();
        menu = loginService.getMenu(id);
        return menu;
    }
}