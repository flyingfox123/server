package com.manyi.base.entity;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface MsgCode {

    /*
       请求成功
     */
    public static int OK = 200;

    public static String SUCCESS = "success";

    /*
       请求参数不正确
   */
    public static int  BAD_REQUEST = 400;

    public static String BAD_REQUEST_MSG = "bad request";

    /*
       请求未找到
   */
    public static int  NOT_FOUND= 404;

    public static String NOT_FOUND_MSG = "request resource not found";

    /*
    请求超时
    */
    public static int TIME_OUT= 408;

    public static String TIME_OUT_MSG = "request time out";


    /*
    异常
    */
    public static int  EXCEPTATION_FAILED = 417;

    public static String EXCEPTATION_FAILED_MSG = "unknow exceptation";

    /*
      内部异常
   */
    public static int  INTERNAL_SERVER_ERROR = 500;

    public static String INTERNAL_SERVER_ERROR_MSG = "internal sever error";

    /*
      空指针异常
    */
    public static int  EMPTY_OBJ= 401;

    public static String EMPTY_OBJ_MSG = "the object is empty";

}
