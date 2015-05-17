package com.manyi.business.freight.bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public class FreightGuaranteeBean {
    private long id;
    private String applyUserId;
    private String carrierID;
    private String origin;
    private String destination;
    private String plateNo;
    private Date departureDate;
    private Date arriveDate;
    private String consignees;
    private BigDecimal freight;
    private Date createTime;
    private String orderState;
    private boolean IsApplyInvoice;
    private String Remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getCarrierID() {
        return carrierID;
    }

    public void setCarrierID(String carrierID) {
        this.carrierID = carrierID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getConsignees() {
        return consignees;
    }

    public void setConsignees(String consignees) {
        this.consignees = consignees;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public boolean isApplyInvoice() {
        return IsApplyInvoice;
    }

    public void setApplyInvoice(boolean isApplyInvoice) {
        IsApplyInvoice = isApplyInvoice;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
