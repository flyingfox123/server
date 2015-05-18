package com.manyi.business.etc.bean;

import com.manyi.business.order.bean.OrderItem;

import java.sql.Date;

/**
 * Created by Administrator on 2015/5/11.
 */
public class Etc extends OrderItem{

    private long etcId ;

    private String ETCCode;

    private long orderItemId;

    private String plateNum;

    public long getEtcId() {
        return etcId;
    }

    public void setEtcId(long etcId) {
        this.etcId = etcId;
    }

    public String getETCCode() {
        return ETCCode;
    }

    public void setETCCode(String ETCCode) {
        this.ETCCode = ETCCode;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

}
