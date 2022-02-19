package com.pokweb.web.login.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private String pokid;
    private String username;
    private String password;

    public String getPokid() {
        return pokid;
    }

    public void setPokid(String pokid) {
        this.pokid = pokid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
