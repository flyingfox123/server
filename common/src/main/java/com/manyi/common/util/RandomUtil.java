package com.manyi.common.util;

import java.util.Map;

/**
 * Created by zhangyufeng on 2015/4/23 0023.
 */
public class RandomUtil {

    private static final int MIN=0;
    private static final int MAX=19;
    private static final int BASE=10;
    private static final int MINUS1=1;
    private static final int TIMES=9;

    /**
     * 产生随机数，最长19位
     * @param bit 位数
     * @return
     */
    public static String getRandom(int bit){
        if (bit <= MIN||bit >MAX){
            return null;
        }
        double xs = (double)Math.pow(BASE,bit-MINUS1)*TIMES;
        double bs = (double)Math.pow(BASE,bit-MINUS1);

        double result = Math.random()*xs+bs;
        long resultLong = new Double(result).longValue();
        return String.valueOf(resultLong);
    }

}
