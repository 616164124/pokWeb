package com.pokweb.web.login.bo;

import java.io.Serializable;
import lombok.Data;

/**
 * user_student
 * @author
 */
@Data
public class UserStudent implements Serializable {
    /**
     * 1000011001  10 0001 10 01  10年0001专业10班级01学号
     教师，行政人员，后勤人员 id为8700001 为7位字符
     */
    private Integer id;

    private String name;

    private String password;

    /**
     * 学生，老师，工作人员，后勤人员
     */
    private String identity;

    /**
     * 手机号

     */
    private String phone;

    /**
     * 紧急联系电话
     */
    private String phoneSos;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 专业
     */
    private String major;

    /**
     * 系（例如电子系，土木工程系）
     */
    private String department;

    private static final long serialVersionUID = 1L;
}