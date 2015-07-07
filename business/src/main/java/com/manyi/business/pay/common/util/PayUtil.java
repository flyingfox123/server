package com.manyi.business.pay.common.util;

/**
 * @author ZhangYuFeng on 2015/6/11 0011,16:57.
 * @Description:
 * @reviewer:
 */
public class PayUtil {

    /**
     * 字符串根据分隔符拆成数组
     * @param string
     * @param split
     * @return
     */
    public static String[] getStrArr(String string,String split){
        if (string!=null){
            String[] strings=string.split(split);
            return strings;
        }else {
            return null;
        }
    }
}
