package com.manyi.business.pay.account.support;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.dao.AccountDao;
import com.manyi.business.pay.account.support.entity.AccBankCard;
import com.manyi.business.pay.account.support.entity.Account;
import com.manyi.business.pay.common.Constant;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.XLConfig;
import com.manyi.business.pay.common.constant.ParamKey;
import com.manyi.common.message.MessageService;
import com.manyi.common.util.DoHttpRequest;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.support.entity.BaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账户Service
 * Author：WangPengfei
 * review：
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountDao accountDao;
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    /**
     * 获取账户信息
     * @param accountInfoBean
     */
    @Override
    public AccountInfoBean getAccountInfo(AccountInfoBean accountInfoBean) throws BusinessException {

        if (null == accountInfoBean || 1 > accountInfoBean.getUserId()) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        long userId = accountInfoBean.getUserId();

        AccountInfoBean accountInf = accountDao.getAccountInfo(userId);

        if (null == accountInf) {
            throw new BusinessException(Type.ACC_NOT_EXIST);
        }

        return accountInf;
    }

    /**
     * 获取卡片所属银行
     *
     * @return
     */
    public String getBankName(AccountInfoBean accountIf) throws BusinessException {

        if (null == accountIf || "".equals(accountIf.getCardNo())) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        String cardNo = accountIf.getCardNo();

        // 构建银行卡请求参数
        String reqString = DealParamStr.buildBKReqString(cardNo);

        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("获取卡片所属银行响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        String bankName = respMap.get(ParamKey.BANK_NAME.getParamKey());

        if (null == bankName || respMap.get(ParamKey.MESSAGE_CODE.getParamKey()).equals(Constant.CARDBIN_CODE_NORESULT)) {
            throw new BusinessException(Type.NO_MATCH_BANK);
        }
        return bankName;
    }

    /**
     * 发送短信验证码
     */
    @Override
    public boolean sendMessageCode(AccountInfoBean accountInfoBean) throws Exception {

        if (null == accountInfoBean || "".equals(accountInfoBean.getMobile())) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        String mobile = accountInfoBean.getMobile();

        boolean res = messageService.sendMessageCodeForPurse(mobile);

        if (!res) {
            throw new BusinessException(Type.SEND_CODE_FAIL);
        }
        return true;
    }

    /**
     * 实名认证
     */
    @Override
    public boolean authRealInfo(AccountInfoBean accountInfoBean) throws BusinessException {

        if (!checkVerifyCode(accountInfoBean)) {
            throw new BusinessException(Type.CODE_CHECK_FAIL);
        }

        if (!checkAccountInfoBean(accountInfoBean)) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        // 构造实名认证请求参数
        String reqString = DealParamStr.buildUAReqStr(accountInfoBean);
        // 实名认证接口调用
        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("实名认证响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        if (respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_SUCCESS)) {

            Account account = new Account();
            account.setUserId(accountInfoBean.getUserId());
            account.setBalance(new BigDecimal(0));
            account.setRealName(accountInfoBean.getRealName());
            account.setIdCardNo(accountInfoBean.getIdCardNo());
            account.setCreateTime(new Date());
            account.setUpdateTime(new Date());
            account.setType(Constant.DRIVER);
            account.setState(Constant.NORMAL);

            accountDao.saveAccount(account);

            AccBankCard accBankCard = new AccBankCard();
            accBankCard.setAccountId(account.getAccountId());
            accBankCard.setCardNo(accountInfoBean.getCardNo());
            accBankCard.setMobile(accountInfoBean.getMobile());
            accBankCard.setBankName(accountInfoBean.getBankName());
            accBankCard.setBankCode(accountInfoBean.getBankCode());
            accBankCard.setBankBranch(accountInfoBean.getBankBranch());
            accBankCard.setCrBankType(accountInfoBean.getCrBankType());
            accBankCard.setProvNo(accountInfoBean.getProvNo());
            accBankCard.setBindState(Constant.HASBIND);

            accountDao.saveAccBankCard(accBankCard);

            // 查询联行号并更新银行账户信息
            LBankNoQueryAndUpdateInfo(accountInfoBean, account.getAccountId());

            return true;
        } else if (respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_ALREADY)){
            throw new BusinessException(Type.AUTH_REPEAT);
        } else {
            return false;
        }
    }

    /**
     * 修改实名手机信息
     * @param accountInfoBean
     * @return
     */
    public boolean updateRealMobileInfo(AccountInfoBean accountInfoBean) throws BusinessException{

        if (!checkVerifyCode(accountInfoBean)) {
            throw new BusinessException(Type.CODE_CHECK_FAIL);
        }

        AccountInfoBean accountInfo =  accountDao.getAccountInfo(accountInfoBean.getUserId());
        accountInfoBean.setIdCardNo(accountInfo.getIdCardNo());
        accountInfoBean.setRealName(accountInfo.getRealName());
        accountInfoBean.setCardNo(accountInfo.getCardNo());

        // 构造实名修改请求参数
        String reqString = DealParamStr.buildUAReqStr(accountInfoBean);
        // 实名认证接口调用
        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("实名认证响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        if(respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_SUCCESS) ||
                respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_ALREADY)  ) {

            Account account = new Account();
            account.setUpdateTime(new Date());
            account.setAccountId(accountInfo.getAccountId());
            accountDao.updateAccount(account);

            AccBankCard accBankCard = new AccBankCard();
            accBankCard.setAccountId(accountInfo.getAccountId());
            accBankCard.setMobile(accountInfoBean.getMobile());
            accountDao.updateAccBankCard(accBankCard);

            return true;
        } else {
            throw new BusinessException(Type.AUTH_REALINFO_FAIL);
        }
    }

    /**
     * 修改实名卡号信息
     * @param accountInfoBean
     * @return
     */
    public boolean updateRealBankCardInfo(AccountInfoBean accountInfoBean) throws BusinessException{

        if (!checkVerifyCode(accountInfoBean)) {
            throw new BusinessException(Type.CODE_CHECK_FAIL);
        }

        if (!checkAccountInfoBean(accountInfoBean)) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        AccountInfoBean accountInfo =  accountDao.getAccountInfo(accountInfoBean.getUserId());
        accountInfoBean.setIdCardNo(accountInfo.getIdCardNo());
        accountInfoBean.setRealName(accountInfo.getRealName());

        // 构造实名修改请求参数
        String reqString = DealParamStr.buildUAReqStr(accountInfoBean);

        // 实名认证接口调用
        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("实名认证响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        if(respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_SUCCESS) ||
           respMap.get(ParamKey.SIGN_RESULT.getParamKey()).equalsIgnoreCase(Constant.AUTH_RESULT_ALREADY)  ) {

            Account account = new Account();
            account.setUpdateTime(new Date());
            account.setAccountId(accountInfo.getAccountId());
            accountDao.updateAccount(account);

            AccBankCard accBankCard = new AccBankCard();
            accBankCard.setAccountId(accountInfo.getAccountId());
            accBankCard.setCardNo(accountInfoBean.getCardNo());
            accBankCard.setMobile(accountInfoBean.getMobile());
            accBankCard.setBankName(accountInfoBean.getBankName());
            accBankCard.setBankCode(accountInfoBean.getBankCode());
            accBankCard.setBankBranch(accountInfoBean.getBankBranch());
            accBankCard.setCrBankType(accountInfoBean.getCrBankType());
            accBankCard.setProvNo(accountInfoBean.getProvNo());

            accountDao.updateAccBankCard(accBankCard);

            // 查询联行号并更新银行账户信息
            LBankNoQueryAndUpdateInfo(accountInfoBean, accountInfo.getAccountId());

            return true;
        } else {
            throw new BusinessException(Type.AUTH_REALINFO_FAIL);
        }

    }

    /**
     * 校验短信验证码
     * @param accountInfoBean
     * @return
     */
    private boolean checkVerifyCode(AccountInfoBean accountInfoBean) throws BusinessException {
        // 验证短信校验码
        if (null == accountInfoBean || "".equals(accountInfoBean.getMobile()) || "".equals(accountInfoBean.getVerifyCode())) {
            throw new BusinessException(Type.SYSTEM_ERROR);
        }

        String mobile = accountInfoBean.getMobile();

        String messageCode = accountInfoBean.getVerifyCode();

        boolean res = messageService.isIdentificationCodeValidForPurse(mobile, messageCode);

        return res;
    }

    /**
     * 校验账户实体
     * @param accountInfoBean
     * @return
     */
    private boolean checkAccountInfoBean(AccountInfoBean accountInfoBean) throws BusinessException {
        // 验证账户实体
        if ("".equals(accountInfoBean.getBankName()) || "".equals(accountInfoBean.getBankBranch()) ||
            "".equals(accountInfoBean.getCardNo()) || 1 > accountInfoBean.getUserId()) {
            return false;
        }
        return true;
    }

    /**
     * 查询联行号并更新银行账户信息
     * @return
     */
    public void LBankNoQueryAndUpdateInfo(AccountInfoBean accountInfoBean, long accountId) {
        // 查询联行号并更新账户银行信息
        accountInfoBean.setAccountId(accountId);
        Map<String, String> lBankrespMap = LBankNoQuery(accountInfoBean);

        // 验证签名后。更新银行号到银行账户表
//        if (DealParamStr.verifySignature(lBankrespMap)) {

            accountInfoBean.setLBankNo(lBankrespMap.get(ParamKey.LBANK_NO.getParamKey()));

            // 更新账户表
            updateBankCardInfo(accountInfoBean);
//        } else {
//            // 记录日志
//        }
    }

    /**
     * 联行号查询
     */
    public Map<String, String> LBankNoQuery(AccountInfoBean accountInfoBean) {

        // 模拟数据，先写死，稍后删除
        accountInfoBean.setBankCode("CMB");
        accountInfoBean.setProvNo("15");

        String reqString = DealParamStr.buildBankNoQuery(accountInfoBean);

        String respString = DoHttpRequest.doPostRequest(XLConfig.REQUEST_URL, reqString);

        logger.debug("联行号查询响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        return respMap;
    }

    /**
     * 更新账户银行信息，更新联行号
     */
    private void updateBankCardInfo(AccountInfoBean accountInfoBean) {

        AccBankCard accBankCard = new AccBankCard();
        accBankCard.setlBankNo(accountInfoBean.getLBankNo());
        accBankCard.setAccountId(accountInfoBean.getAccountId());

        accountDao.updateAccBankCard(accBankCard);
    }

    @Override
    public List<AccountInfoBean> queryAccount(AccountInfoBean accountInfoBean) {
        if(null != accountInfoBean && null != accountInfoBean.getUserName()) {
            BaseUser baseUser = userService.getUserByName(accountInfoBean.getUserName());
            if(null != baseUser) {
                accountInfoBean.setUserId(baseUser.getId());
            }
        }
        return accountDao.queryAccount(accountInfoBean);
    }

    @Override
    public long countAccount(AccountInfoBean accountInfoBean) {
        return accountDao.countAccount(accountInfoBean);
    }

    @Override
    public AccountInfoBean getAccountDetail(long accountId) {

        return accountDao.getAccountInfoByAccId(accountId);
    }

    @Override
    public void disableAccount(long accountId) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setState(Constant.DISABLE);
        accountDao.updateAccountState(account);
    }

    @Override
    public void enableAccount(long accountId) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setState(Constant.NORMAL);
        accountDao.updateAccountState(account);
    }

}
