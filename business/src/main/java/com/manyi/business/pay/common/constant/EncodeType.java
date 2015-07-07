package com.manyi.business.pay.common.constant;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/10 0010
 */
public enum EncodeType {

    GBK("00"),
    GB2312("01"),
    UTF8("02");
    private String encodeCode;

    EncodeType(String encodeCode) {
        this.encodeCode = encodeCode;
    }

    public String getEncodeCode() {
        return this.encodeCode;
    }
}
