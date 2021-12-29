package com.pokweb.demo2.service;

import com.pokweb.common.response.R;
import org.springframework.stereotype.Service;

@Service
public interface DemoService {

    R getDemo();

    R setDemo();
}
