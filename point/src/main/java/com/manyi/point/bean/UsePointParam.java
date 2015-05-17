package com.manyi.point.bean;

import com.manyi.base.entity.State;

/**
 * Created by Administrator on 2015/4/8.
 */
public class UsePointParam  extends PointParam
{

    private  int usePoint;

    //消费积分行为为000000
    public static String eventCode = "000000";


    public  UsePointParam(int userId1,String ServiceId1 ,String opUserId1 , int usePoint1)
    {
        super(userId1, ServiceId1,eventCode, opUserId1);
        this.usePoint = usePoint1;
    }

    public int getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(int usePoint) {
        this.usePoint = usePoint;
    }
}
