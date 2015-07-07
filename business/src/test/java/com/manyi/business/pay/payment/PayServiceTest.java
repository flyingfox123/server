package com.manyi.business.pay.payment;

import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.OrderServiceImpl;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.AccountServiceImpl;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.BillServiceImpl;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.BillEnum;
import com.manyi.business.pay.common.constant.TradeState;
import com.manyi.business.pay.common.constant.TradeType;
import com.manyi.business.pay.payment.bean.PaymentBean;
import com.manyi.business.pay.payment.bean.ReqPaymentBean;
import com.manyi.business.pay.payment.support.PayServiceImpl;
import com.manyi.business.pay.payment.support.PayServiceImplTest;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
public class PayServiceTest {

    // 用户id
    private static final long USERID = 67;

    // 金额
    private BigDecimal bigDecimal = new BigDecimal("8888");

    @Autowired
    PayServiceImplTest payService;
    @Autowired
    OrderDao orderDao;

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

    /**
     * 支付请求
     *
     * @return 账单号
     */
    @Test
    public void payRequestTest() throws BusinessException, IOException {

        Order order = createOrder(USERID);

        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setBillNo(order.getSeqNo());

        ReqPaymentBean reqPaymentBean = payService.payRequest(paymentBean);

        assertNotNull(reqPaymentBean);

        // 提交支付请求
        String payResult = payService.commitPayment(reqPaymentBean);

        assertNotNull(payResult);

    }

}
