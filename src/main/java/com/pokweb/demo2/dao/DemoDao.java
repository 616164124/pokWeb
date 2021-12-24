package com.pokweb.demo2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Map;

@Mapper
public interface DemoDao {

    Map<String,String> getDemo();

}
