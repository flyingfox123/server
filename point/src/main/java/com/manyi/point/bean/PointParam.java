package com.manyi.point.bean;

import com.manyi.base.entity.State;
import org.wltea.expression.datameta.Variable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/8.
 */
public class PointParam {

    private int userId;

    private String serviceId;

    private String eventCode;

    private String remarks;

    private String opUserId;

    private Map<String,Float> params;

   // private List<Variable> variables;


    public  PointParam(int userId1,String ServiceId1,String eventCode1,String opUserId1)
    {
        this.eventCode = eventCode1;
        this.opUserId = opUserId1;
        this.serviceId = ServiceId1;
        this.userId = userId1;
    }

    public  PointParam(int userId1,String ServiceId1,String eventCode1,String opUserId1,Map<String,Float> params1)
    {
        this.eventCode = eventCode1;
        this.opUserId = opUserId1;
        this.serviceId = ServiceId1;
        this.userId = userId1;
        this.params =params1;
    }

    public Map<String, Float> getParams() {
        return params;
    }

    public void setParams(Map<String, Float> params) {
        this.params = params;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

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

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

}
