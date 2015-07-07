package com.manyi.business.pay.bill.support.dao;


import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-19
 * @reviewer
 */
public interface BillForManageDao {
    List<Bill> findBill(BillBean billBean);
    int getBillCount(BillBean billBean);
}
