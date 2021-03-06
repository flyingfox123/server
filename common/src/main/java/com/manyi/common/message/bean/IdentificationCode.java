package com.manyi.common.message.bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 是否验证码bean
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-11.
 * @reviewer:
 */
public class IdentificationCode {

    public String id;

    public Date createTime;

    public Date expiredTime;

    public String code;

    public String state;

    public String type;

    public String mobile;

    //有效时间
    public String activeTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
}
