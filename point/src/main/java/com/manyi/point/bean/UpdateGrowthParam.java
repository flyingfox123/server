package com.manyi.point.bean;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class UpdateGrowthParam extends PointParam{

    private  int newGrowth;
    //操作员更新积分行为
    public static final String eventCode = "0000002";

    public  UpdateGrowthParam(long userId1, int newGrowth1)
    {
        super(userId1);
        this.newGrowth = newGrowth1;
    }

    public int getNewGrowth() {
        return newGrowth;
    }

    public void setNewGrowth(int newGrowth) {
        this.newGrowth = newGrowth;
    }
}
