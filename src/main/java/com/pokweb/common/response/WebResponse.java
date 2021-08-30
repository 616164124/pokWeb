package com.pokweb.common.response;


import java.io.Serializable;

/**
 * 返回信息的统一数据形式
 */
public class WebResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultCode = "000000";//成功
    private String resultMsg = "";
    private Object resultObj = null;

    public WebResponse() {
    }

    public WebResponse(String code, String message,Object resultObj) {
        this.resultCode = code;
        this.resultMsg = message;
        this.resultObj=resultObj;
    }
    public static WebResponse ok(String msg){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("000000");
        webResponse.setResultMsg("success");
        webResponse.setResultObj(msg);
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