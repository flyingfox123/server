package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.sql.Date;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class QueryPointLogCondition extends Pagination {

    private long pointId;

    private String starttime;

    private String endtime;

    private long  userId;

    private String eventCode;

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public long getPointId() {
        return pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
}
