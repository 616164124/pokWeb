package com.pokweb.web.register.bo;

import java.io.Serializable;
import lombok.Data;

/**
 * user_work
 * @author 
 */
@Data
public class UserWork implements Serializable {
    private Integer id;

    /**
     * 登录账号
     */
    private String userName;

    private String password;

    private String name;

    private String phone;

    private String phoneSos;

    private String identity;

    /**
     * 电子邮箱
     */
    private String email;

    private static final long serialVersionUID = 1L;
}