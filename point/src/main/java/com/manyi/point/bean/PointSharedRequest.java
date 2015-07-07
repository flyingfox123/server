package com.manyi.point.bean;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class PointSharedRequest {

    private long userId;

    private int sharedChannel;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSharedChannel() {
        return sharedChannel;
    }

    public void setSharedChannel(int sharedChannel) {
        this.sharedChannel = sharedChannel;
    }
}
