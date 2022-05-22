package com.pokweb.web.home.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mikael
 */
@RestController
@RequestMapping("pokweb")
public class HomeController {

    @RequestMapping(value = "main",method = RequestMethod.POST)
    public void getMain(@RequestBody Map<String,String> params){

    }




}
