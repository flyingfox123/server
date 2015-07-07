package com.manyi.business.pay.bill;

import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;

import java.util.List;

/**
 * @Description: 管理控制台查询账单服务
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-19
 * @reviewer
 */
public interface BillForManageService {

    /**
     * 查询账单
     * @param billBean
     * @return
     */
    List<Bill> findBill(BillBean billBean);

    /**
     * 查询账单总页数
     * @param billBean
     * @return
     */
    int getBillCount(BillBean billBean);

}
