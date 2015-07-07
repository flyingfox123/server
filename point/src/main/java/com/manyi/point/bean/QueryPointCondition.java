package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;
import com.manyi.base.entity.State;

import java.awt.*;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class QueryPointCondition extends Pagination {

    private long userId;

    private String starttime;

    private String endtime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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
}
