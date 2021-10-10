package com.pokweb.web.login.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.common.utill.JwtUtil;
import com.pokweb.web.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/pokweb")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;

    /**
     *
     * @param params
     *
     * {
     *   "data": {
     *     "name": "123456",
     *     "password": "123456",
     *     "radio": "xs"
     *   },
     *   "token": "login"
     * }
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public WebResponse loginIn(@RequestBody Map<String, Object> params) {
        logger.info("进入loginIn方法的参数{}",params);
        params = (Map) params.get("data");
        System.out.println(loginService);
        return loginService.login(params);
    }

    @RequestMapping(value = "getMenu", method = RequestMethod.POST)
    public WebResponse getMenu(@RequestBody Map<String, Object> params) {
        Map data = (Map) params.get("data");
        String id = data.get("id").toString();
        WebResponse  menu = loginService.getMenu(id);
        return menu;
    }

    /**
     * 获取验证码
     * @param params
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public WebResponse getVerifycode(Map<String, Object> params){
//        loginService.getVerifcode()
        return WebResponse.ok();
    }
    /**
     * 单独一个接口来验证token是否有效
     * 验证token是否有效
     *
     */
    @RequestMapping(value = "getToken",method = RequestMethod.GET)
    public WebResponse getToken(Map<String, Object> params){

        String token = params.get("token").toString();

        return WebResponse.ok("token有效");
    }

}