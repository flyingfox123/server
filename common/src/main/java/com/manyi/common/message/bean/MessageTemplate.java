package com.manyi.common.message.bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 短信模板bean
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-11.
 * @reviewer:
 */
public class MessageTemplate {

    public String id;

    public String templateId;

    public String content;

    public Date createTime;

    public Date expiredTime;

    public String state;

    public String type;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
