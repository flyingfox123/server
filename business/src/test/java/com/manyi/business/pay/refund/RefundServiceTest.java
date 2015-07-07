package com.manyi.business.pay.refund;

import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.ETCBean;
import com.manyi.business.order.support.EtcInvoiceBean;
import com.manyi.business.order.support.OrderServiceImpl;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.AccountServiceImpl;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.BillServiceImpl;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
import com.manyi.business.pay.refund.support.RefundServiceImplTest;
import com.manyi.common.util.DataUtil;
import com.manyi.common.util.DateUtil;
import com.manyi.common.util.OrderSeqGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-pay-junit.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class RefundServiceTest {

    @Autowired
    RefundServiceImplTest refundService;
    @Autowired
    private OrderServiceImpl orderService;
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
     * 测试退款
     */
    @Test
    public void orderRefund() throws BusinessException, IOException {
        Order order = createOrder(USERID);

        // 创建已支付订单
        BillBean billBean = createBill(order);

        String str = refundService.orderRefund(order.getSeqNo(), USERID, order.getPaidAmount().toString());

        assertEquals("received", str);
    }

    /**
     * 测试退款，请求参数为null
     * @throws BusinessException
     * @throws IOException
     */
    @Test
    public void orderRefundByParamisNull() {

        String str = null;
        try {
            str = refundService.orderRefund(null, 0, "8888");
            assertEquals("received", str);
        } catch (BusinessException ex) {
            assertEquals(Type.SYSTEM_ERROR, ex.getErrorType());
        }
    }

    /**
     * 获取hash后的字符串
     */
    @Test
    public void getMd5String() {
        String string = "char_set=02&partner_id=800053100050002&pay_no=201504030002078771&message_code=000000" +
                "&sign_type=MD5&biz_type=OrderRefund&version_no=1.0&refund_amount=8888" +
                "&order_id=2015061915231917694&status=SUCCESS&mac=96acb3fd9101d6b045da05884e32eefe";

        Map<String, String> map = DealParamStr.parseQString(string);
        String str = DataUtil.convertMapToStr(map);

        String stt = "02800053100050002201504030002078771000000MD5OrderRefund1.088882015061915231917694SUCCESS";
        System.out.println(DealParamStr.buildXLSignature(stt));
    }
}
