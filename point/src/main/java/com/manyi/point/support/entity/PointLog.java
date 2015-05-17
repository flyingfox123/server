package com.manyi.point.support.entity;

import java.sql.Date;

/**
 * Created by Administrator on 2015/4/8.
 */



public class PointLog {

    private int id;

    private int pointId;

    private String calcFormula;

    private Date createTime;

    private int point;

    private int growth;

    private String remarks;

    private String opUserId;

    private String eventCode;


    public int getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public String getCalcFormula() {
        return calcFormula;
    }

    public void setCalcFormula(String calcFormula) {
        this.calcFormula = calcFormula;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }


}
