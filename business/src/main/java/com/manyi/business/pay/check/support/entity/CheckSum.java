package com.manyi.business.pay.check.support.entity;

import java.sql.Date;

/**
 * @author ZhangYuFeng on 2015/6/24 0024,22:46.
 * @Description:
 * @reviewer:
 */
public class CheckSum {
    private String createDate;
    private int myNo;
    private int myYes;
    private int diff;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getMyNo() {
        return myNo;
    }

    public void setMyNo(int myNo) {
        this.myNo = myNo;
    }

    public int getMyYes() {
        return myYes;
    }

    public void setMyYes(int myYes) {
        this.myYes = myYes;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
