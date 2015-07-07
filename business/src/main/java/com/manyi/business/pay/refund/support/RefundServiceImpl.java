package com.manyi.business.pay.refund.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.XLConfig;
import com.manyi.business.pay.common.constant.*;
import com.manyi.business.pay.refund.RefundService;
import com.manyi.common.util.DoHttpRequest;
import com.manyi.common.util.NumberValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/18
 */
@Service("refundService")
public class RefundServiceImpl implements RefundService {

    private Logger logger = LoggerFactory.getLogger(RefundServiceImpl.class);

    @Autowired
    BillService billService;
    @Autowired
    AccountService accountService;

    // 退卷结果为received，银行已经受理
    private static String RES_REC = "received";

    /**
     * 订单退款
     * @param orderNo
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public String orderRefund(String orderNo, long userId, String amount) throws BusinessException {

        if (null == orderNo || "".equals(orderNo) || userId < 1) {
            throw new BusinessException(Type.SYSTEM_ERROR.getErrorCode());
        }

        if (!NumberValidationUtils.isRealNumber(amount)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        // 查询账户信息，获取银行卡等结算要素
        AccountInfoBean accountInfo = new AccountInfoBean();
        accountInfo.setUserId(userId);
        AccountInfoBean infoBean = accountService.getAccountInfo(accountInfo);

        // 创建账单
        BillBean billBean = createBill(orderNo, infoBean.getAccountId(), amount);

        // 查询需要退款的账单
        Bill bill = queryBill(orderNo);

        String reqString = DealParamStr.buildOrderRefund(billBean.getBillNo(), bill.getBillNo(), amount);

        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("订单退款响应的报文=" + respString);

        Map<String, String> resMap = DealParamStr.parseQString(respString);

        if(null != resMap && !"".equals(resMap.get(ParamKey.STATUS.getParamKey()))) {
            if(DealParamStr.verifySignature(resMap)) {
                // 修改账单状态
                updateBill(resMap.get(ParamKey.STATUS.getParamKey()), resMap, billBean.getBillNo());
                return RES_REC;
            } else {
                logger.error("订单退款时,签名认证失败,反馈报文=" + resMap);
                throw new BusinessException(Type.SYSTEM_ERROR.getErrorCode());
            }
        }
        return null;
    }

    /**
     * 创建账单
     */
    private BillBean createBill(String orderNo, long accountId, String amount) {

        // 创建账单
        BillBean billBean = new BillBean();
        billBean.setAccountId(accountId);
        billBean.setAmount(new BigDecimal(amount));
        billBean.setBusinessType(BillEnum.MONEYGUR.getBillEnum());
        billBean.setTradeType(TradeType.REFUND.getTradeType());
        billBean.setOrderNo(orderNo);

        billService.createBill(billBean);

        return billBean;
    }

    /**
     * 查询需要退款的账单
     * @return
     */
    private Bill queryBill(String orderNo) throws BusinessException {

        return billService.findBill(orderNo, BillEnum.MONEYGUR, TradeType.PAYMENT, TradeState.PAYSUCCESS);
    }

    /**
     * 运费结算后修改账单
     * @param settleResult
     */
    private void updateBill(String settleResult, Map<String, String> resMap, String billNo) {
        BillBean billBean = new BillBean();

        // 如果付款结果为银行受理，则是受理成功
        // 如果付款结果为结算成功，则是交易成功
        // 其他情况都默认为交易失败
        if(settleResult.equals(PayStatus.SETTLE_RECEIVED.getPayStatusCode())) {
            billBean.setTradeState(TradeState.ACCEPTSUCCESS.getTradeState());
//        } else if(settleResult.equals(PayStatus.SETTLE_SUCCESS.getPayStatusCode())) {
//            billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
        } else {
            billBean.setTradeState(TradeState.PAYFAIL.getTradeState());
        }

        billBean.setChannelBillNo(resMap.get(ParamKey.PAY_NO.getParamKey()));
        billBean.setBillNo(billNo);
        billBean.setTradeTime(new Date());

        billService.updateBill(billBean);
    }
}
