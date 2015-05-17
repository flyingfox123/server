package com.manyi.point.bean;

/**
 * Created by Administrator on 2015/4/8.
 */
import java.util.*;

public class PointStrategy {

    private int id;

    private String userGroup;

    private String useId;

    private java.util.Date starttime;

    private java.util.Date endtime;

    private int level;

    private char signal;

    private java.lang.Number calcValue;

    private int priority;

    private boolean isvalid;

    private String eventCode;

    private boolean isMutex;

    private String serviceId;

    private String name;

    private String exp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public char getSignal() {
        return signal;
    }

    public void setSignal(char signal) {
        this.signal = signal;
    }

    public Number getCalcValue() {
        return calcValue;
    }

    public void setCalcValue(Number calcValue) {
        this.calcValue = calcValue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isIsvalid() {
        return isvalid;
    }

    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public boolean isMutex() {
        return isMutex;
    }

    public void setMutex(boolean isMutex) {
        this.isMutex = isMutex;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}