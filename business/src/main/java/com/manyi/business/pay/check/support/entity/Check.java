package com.manyi.business.pay.check.support.entity;

import java.sql.Date;

/**
 * @author ZhangYuFeng on 2015/6/16 0016,9:26.
 * @Description:
 * @reviewer:
 */
public class Check {
    private Long checkId;
    private Date checkTime;
    private String checkFileName;

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckFileName() {
        return checkFileName;
    }

    public void setCheckFileName(String checkFileName) {
        this.checkFileName = checkFileName;
    }
}
