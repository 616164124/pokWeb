package com.pokweb.demo2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DEMOUtil {
    private final static Logger logger = LoggerFactory.getLogger(DEMOUtil.class);

    @PostConstruct
    public void test() {
        String s ="test";
        logger.info(" {} @PostConstruct的使用。", "test");
    }
}
