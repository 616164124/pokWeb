package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.service.LoginService;
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
     * @param params
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public  WebResponse loginIn(@RequestBody Map<String, Object> params) {
        System.out.println("====");
        System.out.println(loginService);
        return  loginService.login(params);
    }

    @RequestMapping(value = "getMenu",method = RequestMethod.GET)
    public WebResponse getMenu(@RequestParam String token){
        System.out.println("token======="+token);
        if (token==null ||""==token){
            return null;
        }
        WebResponse menu = loginService.getMenu(token);


        return menu;
    }
}