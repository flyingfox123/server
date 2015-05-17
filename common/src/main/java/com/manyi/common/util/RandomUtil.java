package com.manyi.common.util;

import java.util.Map;

/**
 * Created by zhangyufeng on 2015/4/23 0023.
 */
public class RandomUtil {

    /**
     * 产生随机数，最长19位
     * @param bit 位数
     * @return
     */
    public static String getRandom(int bit){
        if (bit <= 0||bit >19){
            return null;
        }
        double xs = (double)Math.pow(10,bit-1)*9;
        double bs = (double)Math.pow(10,bit-1);

        double result = Math.random()*xs+bs;
        long resultLong = new Double(result).longValue();
        return String.valueOf(resultLong);
    }

}
