package com.manyi.business.pay.common;

/**
 * author：WangPengfei
 * Review：
 * Date：2015/6/3 0003
 * @version: 1.0.0
 */
public class Constant {

    private Constant(){}

    // ************************************ 账户相关的状态 ************************************//
    /**
     * 账户银行卡绑定状态
     * 0：已绑定
     */
    public static final int HASBIND = 0;

    /**
     * 账户银行卡绑定状态
     * 1：未绑定
     */
    public static final int NOTBIND = 1;

    /**
     * 司机
     */
    public static final int DRIVER = 0;

    /**
     * 企业
     */
    public static final int CORPORATION = 1;

    /**
     * 正常
     * 账户状态
     */
    public static final int NORMAL = 1;

    /**
     * 冻结
     * 账户状态
     */
    public static final int DISABLE = 2;

    // ************************************ 公共接口相关 ************************************//

    /**
     * 接口版本
     */
    public static final String VERSION_NO = "1.0";

    /**
     * 实名认证结果——成功
     */
    public static final String AUTH_RESULT_SUCCESS = "0000";

    /**
     * 实名认证结果——已认证过
     */
    public static final String AUTH_RESULT_ALREADY = "0001";

    public static final String BUSI_ID = "1001";

    // ************************************ 卡bin接口相关 ************************************//

    /**
     * 卡bin接口查无结果
     */
    public static final String CARDBIN_CODE_NORESULT = "000001";

    // 字节数组大小
    public static final int BYTESIZE=1024;

}
