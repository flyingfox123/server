package com.manyi.base.entity;

/**
 * Created by Administrator on 2015/4/8.
 */
public enum Type
{

    //正确返回
    SUCCESS("success"),
    // 系统错误
    SYSTEM_ERROR( "01000" ),
    // 内部错误错误
    INTERNAL_ERROR( "02000" ),
    //积分事件未找到
    POINTEVENT_NOT_FOUND( "02001" ),
    //使用积分事件未定义
    USEPOINT_EVENT_NOT_DEFINED( "02002" ),
    //没有可用积分
    NO_POINT_USE( "02003" ),
    //积分不够用
    NOT_ENOUGH_POINT( "02004" ),
    //积分事件未找到
    GROWTH_LEVEL_ERROR( "02002" ),
    // 用户认证错误
    USER_AUTH( "03000" ),
    // 用户认证错误
    REQUEST_AUTH( "03001" ),
    //默认错误
    DEFAULT_ERROR("00000"),
    // 异常代码不存在
    NO_EXCEPTIONMSG("000002"),
    // 手机号不正确
    WRONG_PHONENO("000003"),
    // 模板id为空
    TEMPLATEID_NULL("000004"),
    // 非短信验证码
    WORONG_CODE("000005"),
    NO_EXP( "02005" ),
    PARAM_ERROR("000006"),

   ORDER_STATUS_ERROR("000007"),
    PARA_NULL("005001"),
    PHONEERR_NULL("005002"),
    PASSWORD_WRONG("005003"),
    NOTPIC("005004")
    ;



    private String errorCode;

    Type( String errorCode )
    {
        this.errorCode = errorCode;
    }

    public String getErrorCode()
    {
        return this.errorCode;
    }
}