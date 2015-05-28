package com.manyi.business.etc.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
public class ETCPreChargeRequest {

    private String orderSeq ;

    private Integer state;

    private List<EtcResult> list;

    public String getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<EtcResult> getList() {
        return list;
    }

    public void setList(List<EtcResult> list) {
        this.list = list;
    }
}
