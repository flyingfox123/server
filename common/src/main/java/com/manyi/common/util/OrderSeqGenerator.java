package com.manyi.common.util;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Description: 订单号创建类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class OrderSeqGenerator {

    private static String DATE_FORMAT = "yyyyMMdd";

    private static  String CHANNEL = "10";

    private static  final int MAX_NUM = 999999;
    private static final String FORMAT = "000000";

    private static  final String SUB_FORMAT = "0000";

    private static  final int MAX_LONG = 99;
    private static  final int MIN_LONG = 10;

    private static  final int ORDER_END_NUM = 6;
    private static  final int ITEM_END_NUM = 4;

    public static String generateOrderSeq(int type) throws BusinessException {
         if(type < MIN_LONG || type >MAX_LONG)
         {
             throw new BusinessException(Type.PARAM_ERROR);
         }
        StringBuffer seq = new StringBuffer(getDateString());
        seq.append(type);
        seq.append(CHANNEL);
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(MAX_NUM);
        String str_m = String.valueOf(num);
        str_m=FORMAT.substring(0, ORDER_END_NUM-str_m.length())+str_m;
        seq.append(str_m) ;
        return seq.toString();
    }

//    public static String generateOrderItemSeq(long userId,String type)
//    {
//        StringBuffer seq = new StringBuffer(ORDER_ITEM_PREFIX);
//        Date d=new Date();
//        long time = d.getTime();
//        seq.append(time);
//        //seq.append(type);
//        String str_m = String.valueOf(userId);
//        str_m=FORMAT.substring(0, 10-str_m.length())+str_m;
//        seq.append(str_m);
//        seq.append(RandomUtil.getRandom(4));
//        return seq.toString();
//    }

    public static String getDateString()
    {

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String generateOrderItemSeq(String seqNo, int count) {

        String str_m = String.valueOf(count);
        str_m=SUB_FORMAT.substring(0, ITEM_END_NUM-str_m.length())+str_m;

        return  seqNo+"-"+str_m;
    }
}
