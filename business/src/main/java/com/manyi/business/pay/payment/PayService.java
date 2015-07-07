package com.manyi.business.pay.payment;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.payment.bean.PaymentBean;
import com.manyi.business.pay.payment.bean.ReqPaymentBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/9 0009
 */
public interface PayService {

    /**
     * 支付回调
     */
    boolean payCallback(HttpServletRequest request) throws BusinessException;

    /**
     * 支付请求
     * @return 账单号
     */
    ReqPaymentBean payRequest(PaymentBean paymentBean) throws BusinessException;

    /**
     * 提交支付请求
     */
    String commitPayment(ReqPaymentBean reqPaymentBean);

}
