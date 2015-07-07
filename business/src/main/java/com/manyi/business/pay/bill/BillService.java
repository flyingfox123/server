package com.manyi.business.pay.bill;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.BusinessType;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-5
 * @reviewer
 */
public interface BillService {

    /**
     * 创建账单
     * @param billBean
     */
    void createBill(BillBean billBean);

    /**
     * 修改账单状态
     * @param billBean
     */
    void updateBill(BillBean billBean);

    /**
     * 查询指定账单数据
     * @param billBean
     * @return
     */
    List<Bill> findBill(BillBean billBean);

    /**
     * @param orderNo
     * @param billEnum
     * @param tradeType
     * @param tradeState
     * @return
     */
    Bill findBill(String orderNo,BillEnum billEnum,TradeType tradeType,TradeState tradeState) throws BusinessException;

    /**
     * 查询未处理账单数据
     * @return
     */
    List<Bill> findUnDealBill();
}
