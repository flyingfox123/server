package com.manyi.point.bean;

import com.manyi.base.entity.State;
import org.wltea.expression.datameta.Variable;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class PointParam {

    private long userId;

    private String serviceId;

    private String eventCode;

    private String remarks;

    private String opUserId;

    private Map<String,Float> params;

   // private List<Variable> variables;


    public  PointParam(long userId1)
    {
        this.userId = userId1;
    }

    public  PointParam(long userId1,Map<String,Float> params1)
    {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
