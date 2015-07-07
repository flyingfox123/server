package com.manyi.business.pay.common.constant;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/8 0008
 */
public enum CertificateType {

    // 身份证
    IDCARD("01"),
    // 军官证
    OFFICER("02"),
    // 护照
    PASSPORT("03"),
    // 回乡证
    RETURNHOMECARD("04"),
    // 台胞证
    TWCOMPATRIOTCARD("05"),
    // 警官证
    POLICECARD("06"),
    // 士兵证
    SOLDIERCARD("07"),
    // 其他证
    OTHERCARD("99");


    private String certTypeCode;

    CertificateType( String certTypeCode)
    {
        this.certTypeCode = certTypeCode;
    }

    public String getCertTypeCode()
    {
        return this.certTypeCode;
    }

}
