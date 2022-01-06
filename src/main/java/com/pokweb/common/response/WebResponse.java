package com.pokweb.common.response;



import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * resultCode 为999999时表示该消息不必展示给用户看
 * 为888888时表示resultMsg需要展示给用户看的错误信息
 * 返回信息的统一数据形式
 */
public class WebResponse implements Serializable {
    private static final long serialVersionUID = 12385L;
    private String resultCode = "";
    private String resultMsg = "";
    private Object resultObj = null;
    private LocalDateTime time = LocalDateTime.now();
    private Object identifier = "";//预留位置

    public WebResponse() {
    }

    public WebResponse(String code, String message, Object resultObj) {
        this.resultCode = code;
        this.resultMsg = message;
        this.resultObj = resultObj;
        this.time = LocalDateTime.now();
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
        webResponse.setResultCode("000000");
        webResponse.setResultMsg("success");
        webResponse.setResultObj(obj);
        webResponse.setTime(LocalDateTime.now());
        webResponse.setIdentifier("");
        return webResponse;
    }

    /**
     * ok
     *
     * @return
     */
    public static WebResponse ok() {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("000000");
        webResponse.setResultMsg("success");
        webResponse.setTime(LocalDateTime.now());
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
        webResponse.setResultCode("888888");
        webResponse.setResultMsg(msg);
        webResponse.setResultObj(obj);
        webResponse.setTime(LocalDateTime.now());
        return webResponse;
    }

    /**
     * error
     *
     * @return
     */
    public static WebResponse error() {
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("999999");
        webResponse.setResultMsg("未知异常，请联系管理员");
        webResponse.setTime(LocalDateTime.now());
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Object getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Object identifier) {
        this.identifier = identifier;
    }
}