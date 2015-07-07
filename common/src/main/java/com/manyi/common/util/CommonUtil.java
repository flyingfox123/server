package com.manyi.common.util;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class CommonUtil {

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


    /**
     * 验证密码是否符合规则,大小写字母及数字
     * @param str
     * @return
     */
    public static boolean isPassWord(String str){
        Pattern pattern = Pattern.compile("[a-z0-9A-Z]*");
        Matcher isPassWord = pattern.matcher(str);
        return isPassWord.matches();
    }

    /**
     * 传入时间格式，获取时间
     * @param format "yyyyMMdd hhmmdd"
     * @return
     */
    public static String getTime(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date=new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获取地址:http://ip:port/baseWeb
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
