package com.manyi.common.messagePush.bean;

import com.manyi.base.entity.Pagination;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class QueryPushMessageCondition extends Pagination
{
    private  String currentTime;

    private int  type;

    private  long   userId;

    private  Boolean  readMark;

    private  Boolean  isSended;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Boolean getReadMark() {
        return readMark;
    }

    public void setReadMark(Boolean readMark) {
        this.readMark = readMark;
    }

    public Boolean getIsSended() {
        return isSended;
    }

    public void setIsSended(Boolean isSended) {
        this.isSended = isSended;
    }
}
