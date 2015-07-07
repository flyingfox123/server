package com.manyi.common.message.bean;

/**
 * @Description: 是否验证码bean
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-11.
 * @reviewer:
 */
public class ReqHeaderJson {
    //请求代码(从请求中直接返回)
    public String reqCode;

    //发起方请求时生成并填写，该流水号全局唯一。使用UUID
    public String transactionId;

    //令牌（固定参数20150114151849xRvZQKK0O1）
    public String tokenId;

    //请求时间
    public String reqTime;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

}
