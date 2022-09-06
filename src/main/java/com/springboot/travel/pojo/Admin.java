package com.springboot.travel.pojo;

import java.util.Date;

public class Admin {
    private Integer admin;

    private String username;

    private String password;

    private Date createTime;

    private Integer salt;

    private String adminPortrait;

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getAdminPortrait() {
        return adminPortrait;
    }

    public void setAdminPortrait(String adminPortrait) {
        this.adminPortrait = adminPortrait == null ? null : adminPortrait.trim();
    }
}