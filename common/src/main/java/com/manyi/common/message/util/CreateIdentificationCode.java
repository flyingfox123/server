package com.manyi.common.message.util;

/**
 * 生成6位数字短信验证码
 * Created by zhaoyuxin on 2015/5/4 0004.
 */
public class CreateIdentificationCode {
    public static String createIdentificationCode(){
        String  num = "";
        for (int i = 0; i < 6; i++) {
            num = num + (int)(Math.random() * 10);
        }
        return num;
    }
}
