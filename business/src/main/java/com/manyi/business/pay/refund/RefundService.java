package com.manyi.business.pay.refund;

import com.manyi.base.exception.BusinessException;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/18
 */
public interface RefundService {

    /**
     * 订单退款
     * @param orderNo
     * @param userId 退款接收者的userId
     * @param amount 以分为单位
     * @return
     */
    String orderRefund(String orderNo, long userId, String amount) throws BusinessException;
}
