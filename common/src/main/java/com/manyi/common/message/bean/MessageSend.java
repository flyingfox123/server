package com.manyi.common.message.bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 短信发送bean
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-04.
 * @reviewer:
 */
public class MessageSend {

    public String id;

    public String userId;

    public Date createTime;

    public String content;

    public String state;

    public Date sendTime;

    public String mobile;

    public String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
