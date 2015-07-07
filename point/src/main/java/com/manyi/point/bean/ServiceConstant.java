package com.manyi.point.bean;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class ServiceConstant {

  private ServiceConstant()
  {}
    /*
    满易服务标示
     */
    public static  final String MANYI ="manyi";

    /*
     系统用户
     */
    public static final String SYSTEM ="system";

    /*
     注册行为编码
     */
    public static  final String REGISTER ="000010";

    /*
   分享行为编码
   */
    //微信
    public static final String SHARED_WECHAT ="000020";
    //微博
    public static  final String SHARED_WEIBO ="000021";
    //短信
    public static final String SHARED_SMS ="000022";

    /*
     交易编码
     */
    public static  final String FINISH_ORDER ="000030";
}
