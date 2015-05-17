package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;
import com.manyi.base.entity.State;

import java.awt.*;

/**
 * Created by Administrator on 2015/4/8.
 */
public class QueryPointCondition extends Pagination {

    private int userId;

    private String serviceId;

    private State state;

    private int level;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
