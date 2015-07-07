package com.manyi.business.highwaytoll.bean;

import java.util.Date;

/**
 * @Description: 历史路线bean
 * @author zhaoyuxin
 * @version: 1.0.0  2015-06-16.
 * @reviewer:
 */
public class HistoryRouteBean {

    private long Id;

    private long userId;

    private String beginRoute;

    private String endRoute;

    private String startCity;

    private String endCity;

    private String createDate;

    private double startX;

    private double startY;

    private double endX;

    private double endY;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBeginRoute() {
        return beginRoute;
    }

    public void setBeginRoute(String beginRoute) {
        this.beginRoute = beginRoute;
    }

    public String getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(String endRoute) {
        this.endRoute = endRoute;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }
}
