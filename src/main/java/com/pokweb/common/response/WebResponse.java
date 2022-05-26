package com.pokweb.common.response;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * resultCode 为500时表示该消息不必展示给用户看
 * 为502时表示resultMsg需要展示给用户看的错误信息
 * resultMsg为错误消息
 * 返回信息的统一数据形式
 */
public class WebResponse implements Serializable {
    private static final long serialVersionUID = 12385L;
    private String resultCode = "";
    private String resultMsg = "";
    private Object resultObj = null;
    private String time = LocalDateTime.now().toString();
    private Object placeholder = "";//预留位置

    public void setPlaceholder(Object placeholder) {
        this.placeholder = placeholder;
    }

    public WebResponse() {
    }

    public WebResponse(String code, String message, Object resultObj) {
        this.resultCode = code;
        this.resultMsg = message;
        this.resultObj = resultObj;
        this.time = LocalDateTime.now().toString();
    }

    /**
     * ok
     * set resultObj
     *
     * @param obj
     * @return
     */
    public static WebResponse ok(Object obj) {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("200");
        webResponse.setResultMsg("success");
        webResponse.setResultObj(obj);
        webResponse.setTime(LocalDateTime.now().toString());
        return webResponse;
    }

    /**
     * ok
     *
     * @return
     */
    public static WebResponse ok() {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("200");
        webResponse.setResultMsg("success");
        webResponse.setTime(LocalDateTime.now().toString());
        return webResponse;
    }

    /**
     * error
     * 展示给用户看的错误信息
     *
     * @param msg
     * @param obj
     * @return
     */
    public static WebResponse error(String msg, Object obj) {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("500");
        webResponse.setResultMsg(msg);
        webResponse.setResultObj(obj);
        webResponse.setTime(LocalDateTime.now().toString());
        return webResponse;
    }

    /**
     * error
     *
     * @return
     */
    public static WebResponse error() {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("500");
        webResponse.setResultMsg("fail");
        webResponse.setTime(LocalDateTime.now().toString());
        return webResponse;
    }

    /**
     * @param code 错误代码
     * @param msg  错误信息
     * @param obj  错误数据
     * @return
     */
    public static WebResponse error(String code, String msg, Object obj) {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode(code);
        webResponse.setResultMsg(msg);
        webResponse.setResultObj(obj);
        webResponse.setTime(LocalDateTime.now().toString());
        return webResponse;
    }


    public WebResponse(Object ret_obj) {
        this.setResultObj(ret_obj);
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultObj() {
        return this.resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getPlaceholder() {
        return placeholder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}