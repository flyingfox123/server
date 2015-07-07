package com.manyi.business.pay.common;

import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.common.constant.*;
import com.manyi.business.pay.payment.bean.PaymentBean;
import com.manyi.business.pay.payment.bean.ReqPaymentBean;
import com.manyi.business.pay.settlement.bean.FeeSettlementBean;
import com.manyi.common.util.CommonUtil;
import com.manyi.common.util.DataUtil;
import com.manyi.common.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/8 0008
 */
public class DealParamStr {

    private static Logger logger = LoggerFactory.getLogger(DealParamStr.class);

    /**
     * 构建卡bin请求参数
     *
     * @param cardNo
     * @return
     */
    public static String buildBKReqString(String cardNo) {
        Map<String, String> reqMap = new LinkedHashMap<String, String>();

        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), Constant.VERSION_NO);
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), BusinessType.CARDBINQUERY.getBusinessType());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.CARD_NO.getParamKey(), DESedeEncrypt.encrypt(XLConfig.STRKEY, cardNo));

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put("mac", buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造实名认证请求参数
     *
     * @return
     */
    public static String buildUAReqStr(AccountInfoBean accountInfoBean) {
        Map<String, String> reqMap = new LinkedHashMap<String, String>();

        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), BusinessType.UPAUTHENTICATE.getBusinessType());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), Constant.VERSION_NO);
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.BUSI_ID.getParamKey(), Constant.BUSI_ID);
        reqMap.put(ParamKey.SIGN_OBJ_ID.getParamKey(), accountInfoBean.getUserName());
        reqMap.put(ParamKey.CUSTOMER_NAME.getParamKey(), DataUtil.toHexString(accountInfoBean.getRealName()));
        reqMap.put(ParamKey.CERT_TYPE.getParamKey(), CertificateType.IDCARD.getCertTypeCode());
        reqMap.put(ParamKey.CERT_NO.getParamKey(), DESedeEncrypt.encrypt(XLConfig.STRKEY, accountInfoBean.getIdCardNo()));
        reqMap.put(ParamKey.CARD_TYPE.getParamKey(), CardType.SAVINGSCARD.getCardType());
        reqMap.put(ParamKey.CARD_NO.getParamKey(), DESedeEncrypt.encrypt(XLConfig.STRKEY, accountInfoBean.getCardNo()));
        reqMap.put(ParamKey.CARD_TEL.getParamKey(), accountInfoBean.getMobile());

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造获取对账文件url请求的参数
     *
     * @return
     */
    public static String buildGetCheckFileUrl() {
        Map<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.REQUEST_ID.getParamKey(), "");
        reqMap.put(ParamKey.SIGN_TYPE.getParamKey(), ParamValue.MD5.getParamValue());
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), ParamValue.CHECKORDER.getParamValue());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), ParamValue.VERSION_NO_1.getParamValue());
        reqMap.put(ParamKey.CHECK_DT.getParamKey(), CommonUtil.getTime("yyyyMMdd hhmmdd"));

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造开户行查询的参数
     *
     * @return
     */
    public static String buildBankNoQuery(AccountInfoBean accountInfoBean) {

        Map<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), ParamValue.VERSION_NO_1.getParamValue());
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), BusinessType.LBANKNOQUERY.getBusinessType());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.BANK_CODE.getParamKey(), accountInfoBean.getBankCode()); // 银行编码  如：CMB
        reqMap.put(ParamKey.PROVINCE_CODE.getParamKey(), accountInfoBean.getProvNo()); // 省份编号 如：山东编号为15

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造运费结算请求的参数
     *
     * @return
     */
    public static String buildLogisticsSettlement(FeeSettlementBean feeSettlement) {
        Map<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), ParamValue.VERSION_NO_1.getParamValue());
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), BusinessType.LOGISTICSSETTLEMENT.getBusinessType());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.SIGN_OBJ_ID.getParamKey(), feeSettlement.getUserName());
        reqMap.put(ParamKey.CRACCNAME.getParamKey(), DataUtil.toHexString(feeSettlement.getRealName()));
        reqMap.put(ParamKey.CRPROV.getParamKey(), feeSettlement.getProvNo());
        reqMap.put(ParamKey.CRBANKNAME.getParamKey(), DataUtil.toHexString(feeSettlement.getBankName()));
        reqMap.put(ParamKey.CRBANKNO.getParamKey(), feeSettlement.getLBankNo());
        reqMap.put(ParamKey.CRBANKTYPE.getParamKey(), feeSettlement.getCrBankType());
        reqMap.put(ParamKey.CRACCNO.getParamKey(), feeSettlement.getCardNo());
        reqMap.put(ParamKey.LOGISTICSAMT.getParamKey(), feeSettlement.getLogisticsAmt().toString());
        reqMap.put(ParamKey.REQSEQNO.getParamKey(), feeSettlement.getBillNo());

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造退款的参数
     *
     * @return
     */
    public static String buildOrderRefund(String billNo, String refundNo, String amount) {

        Map<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.REQUEST_ID.getParamKey(), billNo);
        reqMap.put(ParamKey.SIGN_TYPE.getParamKey(), ParamValue.MD5.getParamValue());
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), BusinessType.ORDERREFUND.getBusinessType());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), ParamValue.VERSION_NO_1.getParamValue());
        reqMap.put(ParamKey.ORDER_ID.getParamKey(), refundNo);
        reqMap.put(ParamKey.REFUND_AMOUNT.getParamKey(), amount);
        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        return DataUtil.createMapToStr(reqMap);
    }

    /**
     * 构造支付请求参数(模拟信联)
     *
     * @param paymentBean
     * @return
     */
    public static ReqPaymentBean buildPayReqParam(PaymentBean paymentBean) {

        Map<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put(ParamKey.CHAR_SET.getParamKey(), EncodeType.UTF8.getEncodeCode());
        reqMap.put(ParamKey.VERSION_NO.getParamKey(), ParamValue.VERSION_NO_1.getParamValue());
        reqMap.put(ParamKey.PARTNER_ID.getParamKey(), XLConfig.PARTNER_ID);
        reqMap.put(ParamKey.BIZ_TYPE.getParamKey(), "payment");
        reqMap.put(ParamKey.SIGN_TYPE.getParamKey(), ParamValue.MD5.getParamValue());
        reqMap.put("total_amount", paymentBean.getAmount().toString());
        reqMap.put(ParamKey.REQUEST_ID.getParamKey(), paymentBean.getBillNo());

        String reqString = DataUtil.convertMapToStr(reqMap);

        reqMap.put(ParamKey.MAC.getParamKey(), buildXLSignature(reqString));

        ReqPaymentBean reqPaymentBean = new ReqPaymentBean();
        reqPaymentBean.setChar_set(reqMap.get(ParamKey.CHAR_SET.getParamKey()));
        reqPaymentBean.setVersion_no(reqMap.get(ParamKey.VERSION_NO.getParamKey()));
        reqPaymentBean.setPartner_id(reqMap.get(ParamKey.PARTNER_ID.getParamKey()));
        reqPaymentBean.setBiz_type(reqMap.get(ParamKey.BIZ_TYPE.getParamKey()));
        reqPaymentBean.setSign_type(reqMap.get(ParamKey.SIGN_TYPE.getParamKey()));
        reqPaymentBean.setTotal_amount(reqMap.get("total_amount"));
        reqPaymentBean.setRequest_id(reqMap.get(ParamKey.REQUEST_ID.getParamKey()));
        reqPaymentBean.setMac(reqMap.get(ParamKey.MAC.getParamKey()));

        return reqPaymentBean;
    }

    /**
     * 解析信联支付反馈报文
     *
     * @param request
     * @return
     */
    public static Map<String, String> parseXLPayRespParam(HttpServletRequest request) {
        // 获得响应的报文数据
        String char_set = request.getParameter(ParamKey.CHAR_SET.getParamKey());
        String partner_id = request.getParameter(ParamKey.PARTNER_ID.getParamKey());
        String pay_no = request.getParameter(ParamKey.PAY_NO.getParamKey());
        String message_code = request.getParameter(ParamKey.MESSAGE_CODE.getParamKey());
        String sign_type = request.getParameter(ParamKey.SIGN_TYPE.getParamKey());
        String biz_type = request.getParameter(ParamKey.BIZ_TYPE.getParamKey());
        String version_no = request.getParameter(ParamKey.VERSION_NO.getParamKey());
        String total_amount = request.getParameter(ParamKey.TOTAL_AMOUNT.getParamKey());
        String order_id = request.getParameter(ParamKey.ORDER_ID.getParamKey());
        String pay_date = request.getParameter(ParamKey.PAY_DATE.getParamKey());
        String status = request.getParameter(ParamKey.PAY_STATUS.getParamKey());
        String order_date = request.getParameter(ParamKey.ORDER_DATE.getParamKey());
        String fee = request.getParameter(ParamKey.PAY_FEE.getParamKey());
        String mac = request.getParameter(ParamKey.MAC.getParamKey());

        // 放入map集合
        Map<String, String> resMap = new LinkedHashMap<String, String>();
        resMap.put(ParamKey.CHAR_SET.getParamKey(), char_set);
        resMap.put(ParamKey.PARTNER_ID.getParamKey(), partner_id);
        resMap.put(ParamKey.PAY_NO.getParamKey(), pay_no);
        resMap.put(ParamKey.MESSAGE_CODE.getParamKey(), message_code);
        resMap.put(ParamKey.SIGN_TYPE.getParamKey(), sign_type);
        resMap.put(ParamKey.BIZ_TYPE.getParamKey(), biz_type);
        resMap.put(ParamKey.VERSION_NO.getParamKey(), version_no);
        resMap.put(ParamKey.TOTAL_AMOUNT.getParamKey(), total_amount);
        resMap.put(ParamKey.ORDER_ID.getParamKey(), order_id);
        resMap.put(ParamKey.PAY_DATE.getParamKey(), pay_date);
        resMap.put(ParamKey.PAY_STATUS.getParamKey(), status);
        resMap.put(ParamKey.ORDER_DATE.getParamKey(), order_date);
        resMap.put(ParamKey.PAY_FEE.getParamKey(), fee);
        resMap.put(ParamKey.MAC.getParamKey(), mac);

        return resMap;
    }

    /**
     * 构造信联签名参数
     *
     * @param reqString
     * @return
     */
    public static String buildXLSignature(String reqString) {
        String signStr = reqString + XLConfig.SECRET_KEY;
        System.out.println("准备要签名的串为=" + signStr);
        String sign = null;
        try {
            sign = Md5Util.getMD5Str(signStr);
        } catch (NoSuchAlgorithmException e) {
            logger.error("The DealParamStr buildXLSignature is wrong, NoSuchAlgorithmException =" + e);
        }
        return sign;
    }

    /**
     * 解析返回的报文
     *
     * @param respString
     * @return
     */
    public static Map<String, String> analyzeRespStr(String respString) {
        // 请求要素
        Map<String, String> para;
        if (null != respString && !"".equals(respString)) {
                para = parseQString(respString);
        } else {
            return null;
        }
        return para;
    }

    /**
     * 验证签名
     *
     * @param para
     * @return
     */
    public static boolean verifySignature(Map<String, String> para) {

        String mac = para.get(ParamKey.MAC.getParamKey());

        para.remove(ParamKey.MAC.getParamKey());
        String prepareSignStr = DataUtil.convertMapToStr(para);

        String sign = buildXLSignature(prepareSignStr);

        if (null != mac && "" != mac) {
            if (sign.equalsIgnoreCase(mac)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解析key=value字符串，生成应答要素
     *
     * @param str 需要解析的字符串
     * @return 解析的结果map
     * @throws java.io.UnsupportedEncodingException
     */
    public static Map<String, String> parseQString(String str) {

        Map<String, String> map = new LinkedHashMap<String, String>();
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        char curChar;
        String key = null;
        boolean isKey = true;

        for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
            curChar = str.charAt(i);// 取当前字符

            if (curChar == '&') {// 如果读取到&分割符
                putKeyValueToMap(temp, isKey, key, map);
                temp.setLength(0);
                isKey = true;
            } else {
                if (isKey) {// 如果当前生成的是key
                    if (curChar == '=') {// 如果读取到=分隔符
                        key = temp.toString();
                        temp.setLength(0);
                        isKey = false;
                    } else {
                        temp.append(curChar);
                    }
                } else {// 如果当前生成的是value
                    temp.append(curChar);
                }
            }
        }

        putKeyValueToMap(temp, isKey, key, map);

        return map;
    }

    private static void putKeyValueToMap(StringBuilder temp, boolean isKey, String key, Map<String, String> map) {
        if (isKey) {
            key = temp.toString();
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            map.put(key, "");
        } else {
            if (key.length() == 0) {
                throw new RuntimeException("QString format illegal");
            }
            try {
                map.put(key, URLDecoder.decode(temp.toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error("The DealParamStr putKeyValueToMap is wrong, UnsupportedEncodingException =" + e);
            }
        }
    }

}
