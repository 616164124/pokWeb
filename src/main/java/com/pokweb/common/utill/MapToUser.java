package com.pokweb.common.utill;


import com.pokweb.common.base.User;

import java.util.Map;

public class MapToUser {


    public User toUser(Map<String, String> map){
        User user = new User();
        user.setDepartment(map.get("Department").toString());
        user.setDepartment(map.get("major").toString());
        user.setDepartment(map.get("identity").toString());
        user.setDepartment(map.get("effect").toString());
        user.setDepartment(map.get("name").toString());
        user.setDepartment(map.get("phone_sos").toString());
        user.setDepartment(map.get("email").toString());
        return user;
    }
}
