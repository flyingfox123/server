package com.manyi.point.bean;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class UpdatePointParam extends PointParam{


    private  int newPoint;
    //操作员更新积分行为
    public static final String eventCode = "0000001";


    public  UpdatePointParam(long userId1, int newPoint1)
    {
        super(userId1);
        this.newPoint = newPoint1;
    }

    public int getNewPoint() {
        return newPoint;
    }

    public void setNewPoint(int newPoint) {
        this.newPoint = newPoint;
    }
}
