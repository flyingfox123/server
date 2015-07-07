package com.manyi.common.message.bean;

/**
 * @Description: 短信参数
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-11.
 * @reviewer:
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
