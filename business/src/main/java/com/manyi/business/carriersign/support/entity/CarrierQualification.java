package com.manyi.business.carriersign.support.entity;

import java.sql.Date;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
public class CarrierQualification {

    private long id;

    private String userName;

    private Date createTime;

    private char reviewState;

    private Date reviewTime;

    private Date lastUpdateTime;

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

    public char getReviewState() {
        return reviewState;
    }

    public void setReviewState(char reviewState) {
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
}
