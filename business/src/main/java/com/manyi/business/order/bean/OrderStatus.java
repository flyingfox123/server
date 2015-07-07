package com.manyi.business.order.bean;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class OrderStatus {

    private OrderStatus ()
    {

    }

    public static final String PENDING = "pending";

    public static final String CANCELED = "canceled";

    public static final String PAYING = "paying";

    public static final String PAYED = "payed";

    public static final String PAY_FAILED = "PayFail";

    public static final String DRAWBACK = "drawback";

    public static final String SUCCESS = "success";
}
