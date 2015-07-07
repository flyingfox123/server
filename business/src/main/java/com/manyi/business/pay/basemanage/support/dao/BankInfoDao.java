package com.manyi.business.pay.basemanage.support.dao;

import com.manyi.business.pay.basemanage.support.entity.BankInfo;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-17
 * @reviewer
 */
public interface BankInfoDao {

    /**
     * 查询所有银行信息
     * @return
     */
    List<BankInfo> getBankInfoAll();

    /**
     * 增加银行信息
     * @param bankInfo
     */
    void addBankInfo(BankInfo bankInfo);
}
