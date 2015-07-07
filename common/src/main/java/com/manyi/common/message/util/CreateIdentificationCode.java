package com.manyi.common.message.util;

/**
 * @Description: 生成数字短信验证码
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-04
 * @reviewer:
 */
public class CreateIdentificationCode {
    //验证码位数
    private final static int count=6;
    //数字10
    private final static int num_10=10;

    public static String createIdentificationCode(){
        StringBuilder  num = new StringBuilder();
        for (int i = 0; i < count; i++) {
            num = num.append ((int)(Math.random() * num_10));
        }
        return num.toString();
    }
}
