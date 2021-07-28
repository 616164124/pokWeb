package com.pokweb.web.login.controller;

import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
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
        UserStudent student = new UserStudent();
        student.setDepartment("03");
        student.setEmail("123@qq.com");
        student.setId(1100030112);
        student.setIdentity("9001");
        student.setMajor("00");
        student.setName("学生李");
        student.setPassword("123456");
        student.setPhone("12345678910");
        student.setPhoneSos("12345678910");
        //1000011001 123456
        String id = "1000011001";
        String password="123456";
       //UserStudent student = userStudentDao.selectUserStudent(id, password);
        userStudentDao.insert(student);
        System.out.println("成功。。。。");

    }
}
