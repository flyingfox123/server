package com.manyi.business.freight.bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class FreightChargeBean {
    private long id;
    private long freightGuaranteeID;
    private String serialNo;
    private String payAccID;
    private String receiverAccID;
    private Date createTime;
    private String payStatus;
    private Date payTime;
    private BigDecimal Amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFreightGuaranteeID() {
        return freightGuaranteeID;
    }

    public void setFreightGuaranteeID(long freightGuaranteeID) {
        this.freightGuaranteeID = freightGuaranteeID;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getPayAccID() {
        return payAccID;
    }

    public void setPayAccID(String payAccID) {
        this.payAccID = payAccID;
    }

    public String getReceiverAccID() {
        return receiverAccID;
    }

    public void setReceiverAccID(String receiverAccID) {
        this.receiverAccID = receiverAccID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }
}
