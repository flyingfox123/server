package com.manyi.business.pay.common.util;

import com.manyi.common.util.CommonUtil;
import com.manyi.common.util.RandomUtil;

/**
 * @author ZhangYuFeng on 2015/6/12 0012,8:51.
 * @Description: 账单号生成器
 * @version 1.0.0
 * @reviewer:
 */
public class BillNoGenerator {
    public static final int RANDOMNUM=5;
    public static String getBillNo(){
        String random = RandomUtil.getRandom(RANDOMNUM);
        String currentDate = CommonUtil.getTime("yyyyMMddHHmmdd");
        return currentDate+random;
    }
}
