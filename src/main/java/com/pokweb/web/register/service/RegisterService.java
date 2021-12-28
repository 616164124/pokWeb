package com.pokweb.web.register.service;

import com.pokweb.common.response.WebResponse;

import java.util.Map;

public interface RegisterService {
    WebResponse register(Map<String, Object> params);

    WebResponse sendEmailCode(String code, String email);

    boolean saveRedisFor5(String username, String code);

    void clearRedis(String username);
}
