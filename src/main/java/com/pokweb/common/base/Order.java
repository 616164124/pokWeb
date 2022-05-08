package com.pokweb.common.base;

import lombok.Data;

import java.io.Serializable;
@Data
public class Order implements Serializable {
    private String id;
    private String detail;
    private String product;
    private int number;

}
