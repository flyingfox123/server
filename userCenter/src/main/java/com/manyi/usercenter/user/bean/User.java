package com.manyi.usercenter.user.bean;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/5/13 0013.
 */
public class User {
    private String loginName;
    private String passWord;
    private String secreKey;
    private String type;
    private String state;
    private Date createTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSecreKey() {
        return secreKey;
    }

    public void setSecreKey(String secreKey) {
        this.secreKey = secreKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
