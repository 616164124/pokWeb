package com.pokweb.common.response;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", HttpStatus.OK.value());
        put("msg", "success");
        put("time", LocalDateTime.now());
        put("identifier","");
    }

    /**
     * @return 未知异常，请联系管理员
     */
    public static R error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("time", LocalDateTime.now());
        return r;
    }

    /**
     *
     * @param msg
     * @return r
     */
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        r.put("time", LocalDateTime.now());
        return r;
    }


    public static R ok(int code, Object result) {
        R r = new R();
        r.put("code", code);
        r.putResult(result);
        r.put("time", LocalDateTime.now());
        return r;
    }

    public static R ok(Map<String, ?> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R putResult(Object value) {
        super.put("result", value);
        return this;
    }
}
