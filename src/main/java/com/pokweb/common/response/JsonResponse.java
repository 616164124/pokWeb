package com.pokweb.common.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class JsonResponse extends JSONObject {
    private int code;
    private String msg;
    private Object object;

}
