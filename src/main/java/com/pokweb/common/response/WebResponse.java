package com.pokweb.common.response;


import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回信息的统一数据形式
 */
public class WebResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultCode = "";
    private String resultMsg = "";
    private Object resultObj = null;

    public WebResponse() {
    }

    public WebResponse(String code, String message,Object resultObj) {
        this.resultCode = code;
        this.resultMsg = message;
        this.resultObj=resultObj;
    }

    /**
     * ok
     * set resultObj
     * @param obj
     * @return
     */
    public static WebResponse ok(Object obj){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("000000");
        webResponse.setResultMsg("success");
        webResponse.setResultObj(obj);
        return webResponse;
    }

    /**
     * ok
     *
     * @return
     */
    public static WebResponse ok(){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("000000");
        webResponse.setResultMsg("success");
        return webResponse;
    }

    /**
     * error
     *
     * @param code
     * @param msg
     * @param obj
     * @return
     */
    public static WebResponse error(String code,String msg,Object obj){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode(code);
        webResponse.setResultMsg(msg);
        webResponse.setResultObj(obj);
        return webResponse;
    }

    /**
     * error
     *
     * @return
     */
    public static WebResponse error(){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("99999999");
        webResponse.setResultMsg("未知异常，请联系管理员");
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
}