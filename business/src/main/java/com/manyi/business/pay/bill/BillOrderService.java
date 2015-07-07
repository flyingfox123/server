package com.manyi.business.pay.bill;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.bill.bean.BillBean;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-16
 * @reviewer
 */
public interface BillOrderService {
    void createEtcBill(BillBean billBean) throws BusinessException;
}
