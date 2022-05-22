package com.pokweb.web.thread.controller;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.thread.service.ThreadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @PostMapping("thread/select")
    public WebResponse select() {
        threadService.add();
        return WebResponse.ok();
    }

    @PostMapping("thread/insert")
    public void insert() {
        threadService.insert();
    }

    /**
     * springboot自带的线程池
     */
    @RequestMapping(value = "/ThreadPoolTaskExecutor",method = RequestMethod.GET)
    public void getThread(){

        threadService.PoolTaskExecutor();
    }

}
