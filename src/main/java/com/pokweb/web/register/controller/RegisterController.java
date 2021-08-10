package com.pokweb.web.register.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.register.bo.UserWork;
import com.pokweb.web.register.dao.UserWorkDao;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("register")
public class RegisterController {
    @Resource
    private UserStudentDao userStudentDao;
    @Resource
    private UserWorkDao userWorkDao;

    /**
     * 学生不能注册为正式用户
     * @param params
     * @return
     */
    @RequestMapping(value = "student",method = RequestMethod.POST)
    public WebResponse registerStudent(Map params) {
        UserStudent userStudent = new UserStudent();
        userStudent.setName("学生王");
        int insert = userStudentDao.insert(userStudent);
        WebResponse chengong = new WebResponse("","","");
        return chengong;
    }

    /**
     * TODO 通过布隆过滤器来判断是否已经有相同的用户名（username）
     * @param params
     * @return WebResponse
     */
    @RequestMapping(value = "work",method = RequestMethod.POST)
    public WebResponse registerWork(@RequestBody Map<String, String> params) {
        UserWork userWork = new UserWork();
        //测试
        params.put("username", "yui@163.com");
        if(params.isEmpty()){
            return new WebResponse("444","参数为空",params);
        }
        //增加布隆过滤器
        if(userWorkDao.selectByUserName(params.get("username"))>0){

            return new WebResponse("444",params.get("username")+"\t该用户名已经注册","");
        }
        if(params.get("password").length()<6){
            return new WebResponse("444","密码长度要6位以上",null);
        }
        userWork.setUserName(params.get("username"));
        userWork.setName(params.get("name"));
        userWork.setPhone(params.get("phone"));
        userWork.setPhoneSos(params.get("phonesos"));
        userWork.setPassword(params.get("password"));
        userWork.setIdentity(params.get("identity"));

         int insert = userWorkDao.insert(userWork);
        WebResponse chengong = new WebResponse("200", "chengong",insert);
        return chengong;
    }
}
