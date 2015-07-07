package com.manyi.business.pay.bill.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.dao.BillDao;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.BusinessType;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
import com.manyi.business.pay.common.util.BillNoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-5
 * @reviewer
 */
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public void createBill(BillBean billBean) {
        billBean.setBillNo(BillNoGenerator.getBillNo());
        billDao.createBill(billBean);
    }

    @Override
    public void updateBill(BillBean billBean) {
        billDao.updateBill(billBean);
    }

    @Override
    public List<Bill> findBill(BillBean billBean) {
        if (null != billBean && 0 != billBean.getPageSize() && billBean.getPageNum() > 0) {
            billBean.setPageNum((billBean.getPageNum() - 1) * billBean.getPageSize());
        }
        List<Bill> billList = billDao.findBill(billBean);
        return billList;
    }

    @Override
    public List<Bill> findUnDealBill() {
        List<Bill> billList = billDao.findUnDealBill();
        return billList;
    }

    /**
     * @param orderNo
     * @param billEnum
     * @param tradeType
     * @param tradeState
     * @return
     */
    @Override
    public Bill findBill(String orderNo,BillEnum billEnum,TradeType tradeType,TradeState tradeState) throws BusinessException {
        if (orderNo==null||billEnum.getBillEnum()<= 0 ||tradeType.getTradeType()<=0||tradeState.getTradeState()<0) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        Bill bill = billDao.findBillForRefund(orderNo, billEnum.getBillEnum(), tradeType.getTradeType(), tradeState.getTradeState());
        return bill;
    }

}
