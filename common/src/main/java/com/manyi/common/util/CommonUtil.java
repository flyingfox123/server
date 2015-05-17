package com.manyi.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class CommonUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    /**
     * 验证手机号
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    public static String getTime(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date=new Date();
        return simpleDateFormat.format(date);
    }

    public static void main(String args[]){
        System.out.println(getTime("yyyyMMdd HHmmss"));
    }
}
