package com.manyi.point.bean;

/**
 * Created by Administrator on 2015/4/8.
 */
public class UpdateGrowthParam extends PointParam{

    private  int newGrowth;
    //操作员更新积分行为
    public static String eventCode = "0000002";

    public  UpdateGrowthParam(int userId1,String ServiceId1 ,String opUserId1 , int newGrowth1)
    {
        super(userId1, ServiceId1,eventCode, opUserId1);
        this.newGrowth = newGrowth1;
    }

    public int getNewGrowth() {
        return newGrowth;
    }

    public void setNewGrowth(int newGrowth) {
        this.newGrowth = newGrowth;
    }
}
