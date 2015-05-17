package com.manyi.common.message.bean;

/**
 * Created by zhaoyuxin on 2015/5/11 0011.
 */
public class DataJson {
    //手机号
    public String mobile;

    //内容
    public String content;

    //签名
    public String sign;

    //发送通道号，0系统自动分配
    public String channelID;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
