package com.pokweb.common.base;


import java.io.Serializable;

public class User implements Serializable {
    private final static long SerialVersionUID = 100L;
    private String Department;
    private String major;
    private String identity;
    private String effect;
    private String name;
    private String phone_sos;
    private String email;


    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_sos() {
        return phone_sos;
    }

    public void setPhone_sos(String phone_sos) {
        this.phone_sos = phone_sos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
