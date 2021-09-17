package com.pokweb.web.login.dao;

import com.pokweb.web.login.bo.UserStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface UserStudentDao {
    int insert(UserStudent record);

    int insertSelective(UserStudent record);
    List<Map> getMenu(String id);

    int selectUserStudent(@Param("id") String id, @Param("password") String password);
}