package com.pokweb.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    private String id;
    /**
     * 产品名称
     */
    private String product;
    /**
     * 产品数量
     */
    private int num;
    /**
     * 订单详情
     */
    private String detail;

}
