package com.pokweb.web.thread.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface ThreadDao {
    @Select("select * from admin where pokid='1'")
    List<Map<String,String>> select();


    void insertorupdate(@Param("id")Integer pokid,@Param("username")String username,@Param("password")String password);
}
