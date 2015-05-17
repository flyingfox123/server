package com.manyi.business.carriersign.bean;

import java.sql.Date;
import java.util.List;

/**
 * Created by zhangyufeng on 2015/4/8 0008.
 */
public class CarrierQualificationBean {
    private long id;

    private String userName;

    private Date createTime;

    private String reviewState;

    private Date reviewTime;

    private Date lastUpdateTime;

    private List<CarrierPaperBean> carrierPaperBeanList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReviewState() {
        return reviewState;
    }

    public void setReviewState(String reviewState) {
        this.reviewState = reviewState;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<CarrierPaperBean> getCarrierPaperBeanList() {
        return carrierPaperBeanList;
    }

    public void setCarrierPaperBeanList(List<CarrierPaperBean> carrierPaperBeanList) {
        this.carrierPaperBeanList = carrierPaperBeanList;
    }
}
