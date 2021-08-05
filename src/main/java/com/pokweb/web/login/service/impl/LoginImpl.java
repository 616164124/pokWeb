package com.pokweb.web.login.service.impl;


import com.pokweb.common.response.WebResponse;
import com.pokweb.web.login.bo.UserStudent;
import com.pokweb.web.login.dao.UserStudentDao;
import com.pokweb.web.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Map;

@Service
public class LoginImpl implements LoginService {


    @Resource
    private UserStudentDao userStudentDao;


    @Override
    public WebResponse login(Map params) {
        System.out.println("=====123");
        if((int)params.get("1")==1){
            System.out.println("====");
        }
        else {
            System.out.println("++++++++");
        }


        Map paramsMap = (Map) params;
        UserStudent userStudent1 = userStudentDao.selectUserStudent(paramsMap.get("id").toString(), paramsMap.get("password").toString());
        return new WebResponse("2002", "sjflks", "");
    }

    @Override
    public WebResponse testThread(Map params) {

        return null;
    }

    public static void main(String[] args) {
        System.out.println(Instant.now().toEpochMilli());
    }

}
