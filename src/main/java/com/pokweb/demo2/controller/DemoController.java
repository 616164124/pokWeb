package com.pokweb.demo2.controller;

import com.pokweb.common.response.R;
import com.pokweb.demo2.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("demo2")
public class DemoController {
    @Resource
    public DemoService demoService;


    @GetMapping("getdemo")
    public R getDemo() {
        R demo = demoService.getDemo();
        return demo;
    }

}
