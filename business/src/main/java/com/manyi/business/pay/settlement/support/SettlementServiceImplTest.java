package com.manyi.business.pay.settlement.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.*;
import com.manyi.business.pay.settlement.SettlementService;
import com.manyi.business.pay.settlement.bean.FeeSettlementBean;
import com.manyi.common.util.DataUtil;
import com.manyi.common.util.NumberValidationUtils;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
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
 * @Description 运费结算服务
 * @author WangPengfei
 * @version 1.0.0  2015/6/15
 * @reviewer
 */
@Service("settlementServiceTest")
public class SettlementServiceImplTest implements SettlementService{

    private Logger logger = LoggerFactory.getLogger(SettlementServiceImplTest.class);

    @Autowired
    AccountService accountService;
    @Autowired
    BillService billService;
    @Autowired
    UserService userService;

    // 运费结算结果为success，司机已经到账
    public static final String RES_SUC = "success";
    // 运费结算结果为wait，银行已经受理
    public static final String RES_REC = "received";
    // 运费结算结果为failed，银行付款失败
    public static final String RES_FAIL = "failed";
    // 运费结算结果为failed，银行已退汇
    public static final String RES_REF = "refund";

    /**
     * 运费结算
     */
    @Override
    public String settlement(String orderNo, long userId, String amount) throws BusinessException {

        if (null == orderNo || "".equals(orderNo) || 1 > userId) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if (!NumberValidationUtils.isRealNumber(amount)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        // 查询账户信息，获取银行卡等结算要素
        AccountInfoBean accountInfo = new AccountInfoBean();
        accountInfo.setUserId(userId);
        AccountInfoBean infoBean = accountService.getAccountInfo(accountInfo);

        // 创建账单
        BillBean billBean = createBill(orderNo, infoBean, amount);

        // 获取用户账号
        BaseUser baseUser = userService.findBaseUserById(userId);

        FeeSettlementBean feeSettlement = new FeeSettlementBean();
        feeSettlement.setUserName(baseUser.getLoginName());
        feeSettlement.setRealName(infoBean.getRealName());
        feeSettlement.setProvNo(infoBean.getProvNo());
        feeSettlement.setBankName(infoBean.getBankName());
        feeSettlement.setLBankNo(infoBean.getLBankNo());
        feeSettlement.setCrBankType(infoBean.getCrBankType());
        feeSettlement.setCardNo(infoBean.getCardNo());
        feeSettlement.setLogisticsAmt(new BigDecimal(amount));
        feeSettlement.setBillNo(billBean.getBillNo());

        String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String respString = "char_set=02&partner_id=800053100050002&sign_obj_id=13552012379&logisticsAmt=4&" +
                "reqSeqNo=2015061916001984976&resSeqNo=2015062076131431348&settle_time=" + dateFormat + "&settle_result=0000&" +
                "settle_result_desc=交易成功";

        Map<String, String> map = DealParamStr.parseQString(respString);

        String prepareSignString = DataUtil.convertMapToStr(map);

        String mac = "&mac=" + DealParamStr.buildXLSignature(prepareSignString);

        String respStr = respString + "&mac=" + mac;
        logger.debug("运费结算响应的报文=" + respStr);

        Map<String, String> resMap = DealParamStr.parseQString(respStr);

        if(resMap.size() > 0) {
            if(DealParamStr.verifySignature(resMap)) {
                // 修改账单状态
                String settleResult = resMap.get(ParamKey.SETTLE_RESULT.getParamKey());
                String settleTime = resMap.get(ParamKey.SETTLE_TIME.getParamKey());
                String resSeqNo = resMap.get(ParamKey.RESSEQNO.getParamKey());

                updateBill(billBean.getBillNo(), settleResult, settleTime, resSeqNo);
                return RES_REC;
            } else {
                logger.error("运费结算时,签名认证失败,反馈报文=" + resMap);
                throw new BusinessException(Type.SYSTEM_ERROR.getErrorCode());
            }
        }
        return null;
    }

    /**
     * 创建账单
     * @param orderNo
     * @param accountInfo
     * @param amount
     * @return
     */
    private BillBean createBill(String orderNo, AccountInfoBean accountInfo, String amount) {

        // 创建账单
        BillBean billBean = new BillBean();
        billBean.setAccountId(accountInfo.getAccountId());
        billBean.setAmount(new BigDecimal(amount));
        billBean.setBusinessType(BillEnum.MONEYGUR.getBillEnum());
        billBean.setTradeType(TradeType.RECEIVABLE.getTradeType());
        billBean.setOrderNo(orderNo);

        billService.createBill(billBean);

        return billBean;
    }

    /**
     * 运费结算后修改账单
     * @param settleResult
     */
    private void updateBill(String billNo, String settleResult, String settleTime, String resSeqNo) {
        BillBean billBean = new BillBean();

        // 如果付款结果为银行受理，则是受理成功
        // 如果付款结果为结算成功，则是交易成功
        // 其他情况都默认为交易失败
        if(settleResult.equals(PayStatus.SETTLE_RECEIVED.getPayStatusCode())) {
            billBean.setTradeState(TradeState.ACCEPTSUCCESS.getTradeState());
        } else if(settleResult.equals(PayStatus.SETTLE_SUCCESS.getPayStatusCode())) {
            billBean.setTradeState(TradeState.PAYSUCCESS.getTradeState());
        } else {
            billBean.setTradeState(TradeState.PAYFAIL.getTradeState());
        }

        try {
            billBean.setTradeTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(settleTime));
        } catch (ParseException e) {
            logger.error("The SettlementServiceImpl is wrong when covered date =" + e);
        }
        billBean.setChannelBillNo(resSeqNo);
        billBean.setBillNo(billNo);

        billService.updateBill(billBean);
    }
}
