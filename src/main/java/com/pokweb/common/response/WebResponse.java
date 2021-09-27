package com.pokweb.common.response;



import java.io.Serializable;

/**
 * resultCode 为999999时表示该消息不必展示给用户看
 * 为888888时表示resultMsg需要展示给用户看的错误信息
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
     *展示给用户看的错误信息
     * @param msg
     * @param obj
     * @return
     */
    public static WebResponse error(String msg,Object obj){
        WebResponse webResponse = new WebResponse();
        webResponse.setResultCode("888888");
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
        webResponse.setResultCode("999999");
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