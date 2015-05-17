package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.sql.Date;


/**
 * Created by Administrator on 2015/4/8.
 */
public class QueryPointLogCondition extends Pagination {

    private int pointId;

    private Date starttime;

    private Date endtime;

    private int userId;

    private String eventCode;

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
}
