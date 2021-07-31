package com.pokweb.common.response;


import java.io.Serializable;

public class WebResponse implements Serializable {
    private String resultCode = "000000";
    private String resultMsg = "";
    private Object resultObj = null;

    public WebResponse() {
    }

    public WebResponse(String message) {
        this.resultMsg = message;
    }

    public WebResponse(String code, String message,Object resultObj) {
        this.resultCode = code;
        this.resultMsg = message;
        this.resultObj=resultObj;
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