package com.pokweb.common.response;


import java.io.Serializable;

public class WebResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String resultCode = "000000";
    private String resultMsg = "";
    private Object resultObj = null;

    public WebResult() {
    }

    public WebResult(String message) {
        this.resultMsg = message;
    }

    public WebResult(String code, String message) {
        this.resultCode = code;
        this.resultMsg = message;
    }

    public WebResult(Object ret_obj) {
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
