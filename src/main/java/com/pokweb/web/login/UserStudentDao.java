package com.pokweb.web.login;

import com.pokweb.web.login.UserStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserStudentDao {
    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    UserStudent selectUserStudent(@Param("id") String id, @Param("password") String password);
}