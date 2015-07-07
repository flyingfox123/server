package com.manyi.business.pay.account.support.dao;

import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.entity.AccBankCard;
import com.manyi.business.pay.account.support.entity.Account;

import java.util.List;

/**
 * 账户dao
 * author：WangPengfei
 * review：
 */
public interface AccountDao {

    /**
     * 获取账户信息，通过用户Id
     * @param userId
     * @return
     */
    AccountInfoBean getAccountInfo(long userId);

    /**
     * 获取账户信息，通过账户Id
     * @param accountId
     * @return
     */
    AccountInfoBean getAccountInfoByAccId(long accountId);

    /**
     * 保存账户信息
     * @param account
     * @return
     */
    void saveAccount(Account account);

    /**
     * 保存账户银行卡信息
     * @param accBankCard
     */
    void saveAccBankCard(AccBankCard accBankCard);

    /**
     * 修改账户表
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 修改账户银行卡信息
     * @param accBankCard
     */
    void updateAccBankCard(AccBankCard accBankCard);

    /**
     * 分页查询账户
     * @param accountInfoBean
     * @return
     */
    List<AccountInfoBean> queryAccount(AccountInfoBean accountInfoBean);

    /**
     * 修改账户状态
     */
    void updateAccountState(Account account);

    /**
     * 统计账户(按照条件)
     * @param accountInfoBean
     * @return
     */
    long countAccount(AccountInfoBean accountInfoBean);

}
