package com.manyi.point.support.entity;

import java.sql.Date;

/**
 * Created by Administrator on 2015/4/24.
 */
public class DimensionExp {

    private int id ;

    private String  dimensionCode ;

    private String  preferentialCode ;

    private String   exp;

    private int dateLimit;

    private Date startDate ;

    private Date endDate ;

    private String  comments ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode;
    }

    public String getPreferentialCode() {
        return preferentialCode;
    }

    public void setPreferentialCode(String preferentialCode) {
        this.preferentialCode = preferentialCode;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(int dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
