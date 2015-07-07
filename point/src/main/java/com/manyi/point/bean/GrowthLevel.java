package com.manyi.point.bean;

/**
 * @Description: 成长值类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class GrowthLevel {

    private int id;

    private String serviceId;

    private int startValue;

    private int endValue;

    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getEndValue() {
        return endValue;
    }

    public void setEndValue(int endValue) {
        this.endValue = endValue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
