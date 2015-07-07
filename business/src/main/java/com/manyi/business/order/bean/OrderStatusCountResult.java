package com.manyi.business.order.bean;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class OrderStatusCountResult {

    private int totalCount;

    private int pendCount;

    private int otherCount;

    private int unDoCount;

    public int getUnDoCount() {
        return unDoCount;
    }

    public void setUnDoCount(int unDoCount) {
        this.unDoCount = unDoCount;
    }

    public int getPendCount() {
        return pendCount;
    }

    public void setPendCount(int pendCount) {
        this.pendCount = pendCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
