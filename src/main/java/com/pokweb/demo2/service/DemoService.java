package com.pokweb.demo2.service;

import com.pokweb.common.response.R;
import org.springframework.stereotype.Service;

public interface DemoService {

    R getDemo();

    R setDemoException();
    void send(String msg);
    void getDemo1(String str, String str1, String... str3);
}
