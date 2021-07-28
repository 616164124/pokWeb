package com.pokweb.web.login.controller;

import com.pokweb.web.login.UserStudent;
import com.pokweb.web.login.UserStudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/pokweb")
public class LoginController {

    @Resource
    private UserStudentDao userStudentDao;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public void insertUserStudentDao(Map<String,Object> params){
        //String username = params.get("username").toString();
        //1000011001 123456
        String id = "1000011001";
        String password="123456";
       UserStudent student = userStudentDao.selectUserStudent(id, password);
        System.out.println("成功。。。。");
    }
}
