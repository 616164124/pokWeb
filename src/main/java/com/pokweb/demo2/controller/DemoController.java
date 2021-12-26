package com.pokweb.demo2.controller;

import com.pokweb.common.response.R;
import com.pokweb.common.response.WebResponse;
import com.pokweb.demo2.service.DemoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("demo2")
public class DemoController {
    @Resource
    public DemoService demoService;


    @PostMapping("getdemo")
    public R getDemo(@RequestBody Map<String,String> params) {
        params.forEach((k,v)->{
            System.out.println("k="+k+"\tv="+v);
        });
        R demo = demoService.getDemo();
        return R.ok();
    }
    @PostMapping("getdemo2")
    public WebResponse getDemo2(@RequestBody Map<String,String> params) {
        params.forEach((k,v)->{
            System.out.println("k="+k+"\tv="+v);
        });
        R demo = demoService.getDemo();
        return WebResponse.ok();
    }

}
