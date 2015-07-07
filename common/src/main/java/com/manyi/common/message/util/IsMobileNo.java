package com.manyi.common.message.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 判断是否手机号码
 * @author zhaoyuxin
 * @version: 1.0.0  2015-05-07.
 * @reviewer:
 */
public class IsMobileNo {
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
