package com.manyi.business.pay.payment.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.PayChannel;
import com.manyi.business.order.bean.QueryOrderCondition;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.*;
import com.manyi.business.pay.payment.PayService;
import com.manyi.business.pay.payment.bean.PaymentBean;
import com.manyi.business.pay.payment.bean.ReqPaymentBean;
import com.manyi.common.util.DataUtil;
import com.manyi.common.util.DoHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/9 0009
 */
@Service("payServiceTest")
public class PayServiceImplTest implements PayService {

    private Logger logger = LoggerFactory.getLogger(PayServiceImplTest.class);

    @Autowired
    OrderService orderService;

    @Autowired
    BillService billService;

    @Autowired
    AccountService accountService;

    /**
     * 支付请求
     */
    @Override
    public ReqPaymentBean payRequest(PaymentBean paymentBean) throws BusinessException {

        if (null == paymentBean || "".equals(paymentBean.getOrderNo())) {
            throw new BusinessException(Type.SYSTEM_ERROR.getErrorCode());
        }

        // 获取订单详情
        QueryOrderCondition condition = new QueryOrderCondition();
        condition.setSeqNo(paymentBean.getOrderNo());

        Order order = orderService.queryOrder(condition).get(0);

        // 查询当前用户的账户id
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setUserId(order.getUserId());

        AccountInfoBean accountInfo = accountService.getAccountInfo(accountInfoBean);

        // 构建账单数据
        BillBean billBean = new BillBean();
        billBean.setOrderId(order.getId());
        billBean.setAccountId(accountInfo.getAccountId());
        billBean.setAmount(order.getPaidAmount());
        billBean.setBusinessType(BillEnum.MONEYGUR.getBillEnum());
        billBean.setTradeType(TradeType.PAYMENT.getTradeType());
        billBean.setOrderNo(order.getSeqNo());

        billService.createBill(billBean);

        // 拼接支付请求参数(模拟信联请求参数)
        paymentBean.setBillNo(billBean.getBillNo());
        paymentBean.setAmount(order.getPaidAmount());

        ReqPaymentBean reqPaymentBean = DealParamStr.buildPayReqParam(paymentBean);

        return reqPaymentBean;
    }

    /**
     * 支付回调
     */
    @Override
    public boolean payCallback(HttpServletRequest request) throws BusinessException {

        Map<String, String> resMap = DealParamStr.parseXLPayRespParam(request);

        if (DealParamStr.verifySignature(resMap)) {

            BillBean billBean = new BillBean();

            // 判断支付状态
            // 如果反馈的支付状态为"预登记"，则记录已受理
            // 如果反馈的支付状态为"支付成功"，则记录交易成功
            // 如果反馈的支付状态为"支付关闭"，则记录交易成功
            // 如果反馈的支付状态为其他结果，则记录交易失败
            if (resMap.get(ParamKey.PAY_STATUS.getParamKey()).equals(PayStatus.RECEIVED.getPayStatusCode())) {
                billBean.setTradeState(TradeState.ACCEPTSUCCESS.getTradeState());
            } else if (resMap.get(ParamKey.PAY_STATUS.getParamKey()).equals(PayStatus.TRADE_SUCCESS.getPayStatusCode())) {
                billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
            } else if (resMap.get(ParamKey.PAY_STATUS.getParamKey()).equals(PayStatus.TRADE_CLOSE.getPayStatusCode())) {
                billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
            } else {
                billBean.setTradeState(TradeState.PAYFAIL.getTradeState());
            }

            try {
                billBean.setTradeTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(resMap.get(ParamKey.PAY_DATE.getParamKey())));
            } catch (ParseException e) {
                logger.error("The PayServiceImpl is wrong when covered date =" + e);
            }
            billBean.setChannelBillNo(resMap.get(ParamKey.PAY_NO.getParamKey()));
            billBean.setBillNo(resMap.get(ParamKey.ORDER_ID.getParamKey()));

            billService.updateBill(billBean);

            // 回调订单接口
            Bill bill = billService.findBill(billBean).get(0);

            if (bill != null) {
                orderService.orderPayCallBack(bill.getOrderNo(), resMap.get(ParamKey.TOTAL_AMOUNT.getParamKey()), PayChannel.BANK_CHANNEL, 0);
            }

            return true;
        } else {
            logger.debug("支付回调时签名不正确");
            // 记录异常

            return false;
        }
    }

    /**
     * 提交支付请求（模拟信联）
     */
    public String commitPayment(ReqPaymentBean reqPaymentBean) {

        String str[] = {"BD", "BD", "BD", "BD", "BD", "BD", "BD", "BD", "BD", "BC"};
        int index= (int) (Math.random()*str.length);
        String randStr = str[index];

        // 支付同步回调报文
        String resp = "pay_no=150605000001&status=" + randStr;

        // 异步回调
        asyncCallback(reqPaymentBean, randStr);
        return resp;
    }

    /**
     * 异步报文发送（模拟信联）
     */
    public void asyncCallback(ReqPaymentBean reqPaymentBean, String randStr) {

        String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 支付异步回调报文
        String respStr = "char_set=02&partner_id=800053100050002&pay_no=150605000001&message_code=000000&sign_type=md5" +
                "&biz_type=orderPay&version_no=1.0&total_amount=" + reqPaymentBean.getTotal_amount() + "&order_id=" +
                reqPaymentBean.getRequest_id() + "&pay_date=" + dateFormat + "&status=" + randStr + "&order_date=20150611&fee=1";

        Map<String, String> map = DealParamStr.parseQString(respStr);

        String prepareSignString = DataUtil.convertMapToStr(map);

        String sign = DealParamStr.buildXLSignature(prepareSignString);
        String mac = "&mac=" + sign;

        String URL = "http://127.0.0.1:8080/baseWeb";
        String uri = "/pay/payment/callback";

        String respString = DoHttpRequest.doPostRequest(URL + uri, respStr + mac);

        logger.debug("发送支付结果之后输出回执=" + respString);
    }

}
