package com.manyi.common.message.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否手机号码
 * Created by zhaoyuxin on 2015/5/7 0007.
 */
public class IsMobileNo {
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
