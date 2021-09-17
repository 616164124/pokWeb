package com.pokweb.web.login.service;

import com.pokweb.common.response.WebResponse;
import org.springframework.stereotype.Service;

import java.lang.ref.WeakReference;
import java.util.Map;

public interface LoginService {
    public WebResponse login(Map params);
    public WebResponse getMenu(String params);
    WebResponse testThread(Map params);
    WebResponse getTokens(Map params);
}
