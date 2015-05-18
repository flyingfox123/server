package com.manyi.common.util;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2015/5/5.
 */
public class OrderSeqGenerator {

    private static String DATE_FORMAT = "yyyyMMdd";

    private static  String CHANNEL = "10";

    private static  int MAX = 999999;
    private static String FORMAT = "000000";

    private static String SUB_FORMAT = "0000";

    public static String generateOrderSeq(int type) throws BusinessException {
         if(type < 10 || type >99)
         {
             throw new BusinessException(Type.PARAM_ERROR);
         }
        StringBuffer seq = new StringBuffer(getDateString());
        seq.append(type);
        seq.append(CHANNEL);
        Random random = new Random();
        int num = random.nextInt(MAX);
        String str_m = String.valueOf(num);
        str_m=FORMAT.substring(0, 6-str_m.length())+str_m;
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
        str_m=SUB_FORMAT.substring(0, 4-str_m.length())+str_m;

        return  seqNo+"-"+str_m;
    }
}
