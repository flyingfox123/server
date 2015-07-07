package com.manyi.business.pay.settlement;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.bean.AccountInfoBean;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/15 0015
 */
public interface SettlementService {

    /**
     * 结算
     */
    String settlement(String orderNo, long userId, String amount) throws BusinessException;
}
