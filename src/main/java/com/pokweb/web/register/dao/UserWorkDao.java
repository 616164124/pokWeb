package com.pokweb.web.register.dao;

import com.pokweb.web.register.bo.UserWork;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserWorkDao  {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWork record);

    int insertSelective(UserWork record);

    UserWork selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWork record);
    int selectByUserName(@Param("user_name") String user_name);
    int updateByPrimaryKey(UserWork record);

    int insetAdmin(Map<String,String> params);

    int countAdmin(Map<String, String> params);
}