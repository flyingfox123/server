package com.manyi.business.pay.settlement;

import com.google.gson.Gson;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.AccountServiceImpl;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.BillServiceImpl;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
import com.manyi.business.pay.settlement.support.SettlementServiceImpl;
import com.manyi.business.pay.settlement.support.SettlementServiceImplTest;
import com.manyi.common.util.DataUtil;
import com.manyi.common.util.DateUtil;
import com.manyi.common.util.OrderSeqGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/16 0016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-pay-junit.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class SettlementServiceTest {

    @Autowired
    SettlementServiceImplTest settlementService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    BillServiceImpl billService;
    @Autowired
    AccountServiceImpl accountService;

    Gson gson = new Gson();

    // 用户id
    private static final long USERID = 67;

    // 金额
    private BigDecimal bigDecimal = new BigDecimal("8888");

    // 渠道流水号
    private static String channelBillNo = "2015062076131431348";

    // 创建订单
    private Order createOrder(long userId) throws IOException, BusinessException {
        OrderBean orderBean = new OrderBean();
        orderBean.setUserId(userId);
        orderBean.setPaidAmount(bigDecimal.toString());
        return subscribeTest(orderBean);
    }

    // 创建订单请求
    private Order subscribeTest(OrderBean request) throws BusinessException, IOException {

        Order order = new Order();
        order.setState(OrderStatus.PENDING);
        order.setUserId(request.getUserId());
        order.setPaidAmount(new BigDecimal(request.getPaidAmount()));
        order.setDiscountAmount(new BigDecimal(0));
        order.setPayableAmount(new BigDecimal(request.getPaidAmount()));;
        order.setType(OrderType.FUND_GUARANTEE);
        String orderSeq;
        boolean repeat = true;
        do {
            orderSeq = OrderSeqGenerator.generateOrderSeq(order.getType());
            Order o = orderDao.getOrderBySeq(orderSeq);
            if (null == o) {
                repeat = false;
            }

        }
        while (repeat);
        order.setSeqNo(orderSeq);
        order.setCreateTime(DateUtil.getCurrentString(new Date()));
        orderDao.createOrder(order);

        OrderItem itemGuarantee = new OrderItem();
        itemGuarantee.setSeqNo(OrderSeqGenerator.generateOrderItemSeq(order.getSeqNo(), 1));
        itemGuarantee.setPayableAmount(order.getPaidAmount());
        itemGuarantee.setOrderId(order.getId());
        itemGuarantee.setUserId(order.getUserId());
        itemGuarantee.setDescription(request.getItemNum() + "");
        itemGuarantee.setType(BusinessType.FUND_GUARANTEE);
        itemGuarantee.setState(OrderItemStatus.PENDING);
        itemGuarantee.setCreateTime(DateUtil.getCurrentString(new Date()));
        orderDao.createOrderItem(itemGuarantee);
        return order;
    }

    // 创建已支付的账单
    private BillBean createBill(Order order) throws BusinessException {

        // 查询账户信息，获取银行卡等结算要素
        AccountInfoBean accountInfo = new AccountInfoBean();
        accountInfo.setUserId(USERID);
        AccountInfoBean infoBean = accountService.getAccountInfo(accountInfo);

        BillBean billBean = new BillBean();
        billBean.setAccountId(infoBean.getAccountId());
        billBean.setAmount(new BigDecimal(order.getPaidAmount().toString()));
        billBean.setBusinessType(BillEnum.MONEYGUR.getBillEnum());
        billBean.setTradeType(TradeType.PAYMENT.getTradeType());
        billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
        billBean.setTradeTime(new Date());
        billBean.setChannelBillNo(channelBillNo);
        billBean.setOrderNo(order.getSeqNo());

        billService.createBill(billBean);

        return billBean;
    }

    /**
     * 测试运费结算
     */
    @Test
    public void settlementTest() throws BusinessException, IOException {
        Order order = createOrder(USERID);

        String str = settlementService.settlement(order.getSeqNo(), USERID, order.getPaidAmount().toString());

        assertEquals("received", str);
    }

    /**
     * 获取hash后的字符串
     */
    @Test
    public void getMd5String() {
        String string = "char_set=02&partner_id=800053100050002&sign_obj_id=13552012379&logisticsAmt=4&"+
                "reqSeqNo=2015061916001984976&resSeqNo=2015062076131431348&settle_time=20150620104530&settle_result=0000&"+
                "settle_result_desc=交易成功";

        String sss = "partner_id=800053100050002&card_no=552819CF151E10457A4851BEEA34FE1A9E488E7732E3F04A&bank_name=中国农业银行&bank_code=01030000&message_code=000000";
//        &mac=194a367d8c4c976ff9d00236748e3064
//        5934e64c5201675140701a0474daab04

        Map<String, String> map = DealParamStr.parseQString(sss);
        String str = DataUtil.convertMapToStr(map);
        System.out.println(DealParamStr.buildXLSignature(str));
    }

}
