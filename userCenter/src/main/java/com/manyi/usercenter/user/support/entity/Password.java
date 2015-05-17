package com.manyi.usercenter.user.support.entity;

import java.io.Serializable;

/**
 * Created by Magic on 2015/4/19.
 */
public class Password implements Serializable {
    private long userId;
    private String password;
    private String secretKey;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
