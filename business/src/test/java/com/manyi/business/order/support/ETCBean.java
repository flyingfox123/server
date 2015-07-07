package com.manyi.business.order.support;


/**
 * Created by Administrator on 2015/5/26.
 */
public class ETCBean {

    private long userId;

    private  String state;

    private String payableAmount;

    private String type;

    private String description ;

    private String ETCCode;


    private String plateNum;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getETCCode() {
        return ETCCode;
    }

    public void setETCCode(String ETCCode) {
        this.ETCCode = ETCCode;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
}
