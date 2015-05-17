package com.manyi.point.bean;

/**
 * Created by Administrator on 2015/4/8.
 */
public class UpdatePointParam extends PointParam{


    private  int newPoint;
    //操作员更新积分行为
    public static String eventCode = "0000001";


    public  UpdatePointParam(int userId1,String ServiceId1 ,String opUserId1 , int newPoint1)
    {
        super(userId1, ServiceId1,eventCode, opUserId1);
        this.newPoint = newPoint1;
    }

    public int getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(int newPoint) {
        this.newPoint = newPoint;
    }
}
