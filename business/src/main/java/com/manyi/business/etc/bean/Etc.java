package com.manyi.business.etc.bean;

import com.manyi.business.order.bean.OrderItem;

import java.sql.Date;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
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

    public boolean isObjectLegal()
    {
        if(null == this.getSeqNo() ||
                null == this.getETCCode() ||
                null == this.getPlateNum()||
                null ==this.getPayableAmount()||
                0 == this.getUserId()||
                0==this.getOrderId())
        {
            return false;
        }
        else
        {
            return true;
        }
    }


}
