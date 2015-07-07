package com.manyi.business.pay.account;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.entity.Account;

import java.util.List;

/**
 * 账户Server
 * author：WangPengfei
 * review：
 * data：2015/5/18
 */
public interface AccountService {

    /**
     * 获取账户信息
     * @param accountInfoBean
     */
    AccountInfoBean getAccountInfo(AccountInfoBean accountInfoBean) throws BusinessException;

    /**
     * 获取卡片所属银行
     * @return
     */
    String getBankName(AccountInfoBean accountIf) throws BusinessException;

    /**
     * 发送短信验证码
     */
    boolean sendMessageCode(AccountInfoBean accountInfoBean) throws Exception;

    /**
     * 实名认证
     */
    boolean authRealInfo(AccountInfoBean accountInfoBean) throws BusinessException;

    /**
     * 修改实名手机信息
     */
    boolean updateRealMobileInfo(AccountInfoBean accountInfoBean) throws BusinessException;

    /**
     * 修改实名银行卡信息
     */
    boolean updateRealBankCardInfo(AccountInfoBean accountInfoBean) throws BusinessException;

    /**
     * 查询所有账户
     * @return
     */
    List<AccountInfoBean> queryAccount(AccountInfoBean accountInfoBean);

    /**
     * 统计账户(按照条件)
     * @return
     */
    long countAccount(AccountInfoBean accountInfoBean);

    /**
     * 获取账户详情
     */
    AccountInfoBean getAccountDetail(long accountId);

    /**
     * 修改账户状态
     */
    void disableAccount(long accountId);

    /**
     * 修改账户状态
     */
    void enableAccount(long accountId);
}
