package com.manyi.business.pay.bill.support;

import com.manyi.business.pay.bill.BillForManageService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.dao.BillForManageDao;
import com.manyi.business.pay.bill.support.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-19
 * @reviewer
 */
@Service
public class BillForManageServiceImpl implements BillForManageService {

    @Autowired
    private BillForManageDao billForManageDao;

    @Override
    public List<Bill> findBill(BillBean billBean) {
        List<Bill> billList = billForManageDao.findBill(billBean);
        return billList;
    }

    @Override
    public int getBillCount(BillBean billBean) {
        return billForManageDao.getBillCount(billBean);
    }
}
