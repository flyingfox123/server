package com.manyi.point.bean;

import com.manyi.base.entity.State;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class UsePointParam  extends PointParam
{

    private  int usePoint;

    //消费积分行为为000000
    public static final String eventCode = "000000";


    public  UsePointParam(long userId1, int usePoint1)
    {
        super(userId1);
        this.usePoint = usePoint1;
    }

    public int getUsePoint() {
        return usePoint;
    }

    public void setUsePoint(int usePoint) {
        this.usePoint = usePoint;
    }
}
