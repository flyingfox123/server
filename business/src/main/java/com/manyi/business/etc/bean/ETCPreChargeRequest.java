package com.manyi.business.etc.bean;

import java.util.List;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class ETCPreChargeRequest {

    private String orderSeq ;

    private Integer state;

    private String amount;

    private List<EtcResult> list;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

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

    public boolean  isObjectLegal()
    {

        if(null== this.getOrderSeq() || null ==this.getList())
        {
            return false;

        }
        for(EtcResult re : this.getList())
        {
            if(null == re.getOrderItemSeq() || null == re.getState())
            {
                return false;
            }
        }
        return true;
    }

}
