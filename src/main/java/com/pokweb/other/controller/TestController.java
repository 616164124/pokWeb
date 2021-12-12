package com.pokweb.other.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/test01",method = RequestMethod.GET)
    public String test01(Model model){
        logger.debug("sfsf  {}","tttt");
        Map<String, String> params= new HashMap<>();
        params.put("hello","324");
        model.addAttribute("result",params);
        return "web/test/login";

    }
}
