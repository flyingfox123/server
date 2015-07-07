package com.manyi.business.order.bean;

import com.manyi.base.entity.Pagination;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class QueryOrderCondition extends Pagination {

    private String seqNo;

    private String state;

    private long userId;

    private int type;

    public int getType() {

        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
