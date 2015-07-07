package com.manyi.business.pay.bill.support.dao;

import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-5
 * @reviewer
 */
public interface BillDao {

    /**
     * 创建订单
     * @param billBean
     */
    void createBill(BillBean billBean);

    /**
     * 查询订单
     * @param billBean
     * @return
     */
    List<Bill> findBill(BillBean billBean);

    /**
     * 修改订单状态
     * @param billBean
     */
    void updateBill(BillBean billBean);

    /**
     * 查询未处理账单
     * @return
     */
    List<Bill> findUnDealBill();

    /**
     * @param orderNo
     * @param businessType
     * @param tradeType
     * @param tradeState
     * @return
     */
    Bill findBillForRefund(@Param("orderNo")String orderNo,@Param("businessType")int businessType,
                           @Param("tradeType")int tradeType,@Param("tradeState")int tradeState);
}
