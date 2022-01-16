package com.pokweb.web.menu100.controller;

import com.pokweb.common.response.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pokweb/menu100")
public class Menu100Controller {
    private static final Logger logger = LoggerFactory.getLogger(Menu100Controller.class);

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public WebResponse getMenu100(){
   logger.info("menu100/get");
        return null;
    }

}
