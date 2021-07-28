package com.pokweb.web.login.dao;

import com.pokweb.web.login.bo.UserStudent;
import org.apache.ibatis.annotations.Param;


public interface UserStudentDao {
    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    UserStudent selectUserStudent(@Param("id") String id, @Param("password") String password);
}