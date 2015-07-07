package com.manyi.business.pay.bill.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderType;
import com.manyi.business.order.bean.QueryOrderCondition;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.bill.BillOrderService;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.ChannelType;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
import com.manyi.common.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-16
 * @reviewer
 */
@Service
public class BillOrderServiceImpl implements BillOrderService {

    @Autowired
    OrderService orderService;

    @Autowired
    BillService billService;

    @Autowired
    AccountService accountService;

    @Override
    public void createEtcBill(BillBean billBean) throws BusinessException{

        // 获取订单详情
        QueryOrderCondition condition = new QueryOrderCondition();
        condition.setSeqNo(billBean.getOrderNo());

        Order order = orderService.queryOrder(condition).get(0);

        // 查询当前用户的账户id
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setUserId(order.getUserId());

        AccountInfoBean accountInfo = accountService.getAccountInfo(accountInfoBean);

        // 构建账单数据
        billBean.setOrderId(order.getId());
        billBean.setAccountId(accountInfo.getAccountId());
        billBean.setAmount(order.getPaidAmount());
        billBean.setBusinessType(BillEnum.QC.getBillEnum());
        billBean.setTradeType(TradeType.PAYMENT.getTradeType());
        billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
        billBean.setChannelId(ChannelType.ETC.getChannelType());
        billBean.setCheckState(1);
        billBean.setTradeTime(new Date());
        billService.createBill(billBean);
    }
}
