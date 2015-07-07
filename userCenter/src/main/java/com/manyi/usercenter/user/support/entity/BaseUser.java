package com.manyi.usercenter.user.support.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Magic on 2015/4/15.
 */
public class BaseUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String loginName;
    private String password;
    private String secretKey;
    private String type;
    private String State;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCredentialsSalt() {
        return loginName + secretKey;
    }
}
