package com.manyi.business.pay.payment.bean;

import com.manyi.business.pay.common.XLConfig;
import com.manyi.business.pay.common.constant.EncodeType;
import com.manyi.business.pay.common.constant.ParamKey;
import com.manyi.business.pay.common.constant.ParamValue;
import com.manyi.common.util.DataUtil;

import java.io.Serializable;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/15 0015
 */
public class ReqPaymentBean implements Serializable{

    private String char_set;
    private String version_no;
    private String partner_id;
    private String biz_type;
    private String sign_type;
    private String total_amount;
    private String request_id;
    private String mac;

    public String getChar_set() {
        return char_set;
    }

    public void setChar_set(String char_set) {
        this.char_set = char_set;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getBiz_type() {
        return biz_type;
    }

    public void setBiz_type(String biz_type) {
        this.biz_type = biz_type;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
