package com.manyi.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 数据处理
 * @author WangPengfei
 * @version 1.0.0  2015/5/28
 * @reviewer
 */
public class DataUtil {
    /**
     * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param reqMap Map类型
     */
    public static String createMapToStr(Map<String, String> reqMap) {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> keys = new ArrayList<String>(reqMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            String value = reqMap.get(name);
            stringBuffer.append("&")
                    .append(name)
                    .append("=")
                    .append(value);
        }
        return stringBuffer.substring(1);
    }

    /**
     * 转化为待签名字符串
     *
     * @param reqMap 请求要素
     * @return 拼接成的字符串
     */
    public static String convertMapToStr(Map<String, String> reqMap) {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> keys = new ArrayList<String>(reqMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            String value = reqMap.get(name);
            stringBuffer.append(value);
        }
        return stringBuffer.toString();
    }

    /**
     * 转化字符串为十六进制编码
     *
     * @param string
     * @return
     */
    public static String toHexString(String string) {
        String str = "";

        for (int i = 0; i < string.length(); i++) {
            int ch = (int) string.charAt(i);
            String st = Integer.toHexString(ch);
            str = str + st;
        }
        return str;
    }

}
