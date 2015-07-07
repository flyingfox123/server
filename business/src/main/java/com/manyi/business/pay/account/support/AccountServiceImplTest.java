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
@Service("accountServiceTest")
public class AccountServiceImplTest implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImplTest.class);

    @Autowired
    AccountDao accountDao;
    @Autowired
    MessageService messageService;

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

        String respString = "char_set=02&partner_id=800053100050002&card_no=7103450600DC22BC1A03FB2F93DF88E1" +
                "&bank_name=建设银行&bank_code=03080000&message_code=000000&mac=b546024a88296abfc3312b873041f79a";

        logger.debug("卡bin查询响应的报文=" + respString);

        Map<String, String> respMap = DealParamStr.analyzeRespStr(respString);

        String bankName = "建设银行";

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

        String respString = "char_set=02&partner_id=800053100050002&busi_id=1001&sign_obj_id=13552012379&sign_no=2015061910034505" +
                "&sign_time=20150619104017&sign_result=0000&sign_result_desc=签约成功&mac=81e5f22cae60cb04d77177c09b88b70d";

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
            accBankCard.setBankCode("CMB");
            accBankCard.setCrBankType("308");
            accBankCard.setProvNo("15");
            accBankCard.setBankBranch(accountInfoBean.getBankBranch());
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

        // 实名认证接口调用
        String respString = "char_set=02&partner_id=800053100050002&busi_id=1001&sign_obj_id=13552012379&sign_no=2015061910034505" +
                "&sign_time=20150619104017&sign_result=0000&sign_result_desc=签约成功&mac=81e5f22cae60cb04d77177c09b88b70d";

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

        // 实名认证接口调用
        String respString = "char_set=02&partner_id=800053100050002&busi_id=1001&sign_obj_id=13552012379&sign_no=2015061910034505" +
                "&sign_time=20150619104017&sign_result=0000&sign_result_desc=签约成功&mac=81e5f22cae60cb04d77177c09b88b70d";

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

        accountInfoBean.setLBankNo(lBankrespMap.get(ParamKey.LBANK_NO.getParamKey()));
        // 更新账户表
        updateBankCardInfo(accountInfoBean);
    }

    /**
     * 联行号查询
     */
    public Map<String, String> LBankNoQuery(AccountInfoBean accountInfoBean) {

        // 模拟数据，先写死，稍后删除
        accountInfoBean.setBankCode("CMB");
        accountInfoBean.setProvNo("15");


        String respString = "char_set=02&partner_id=800053100050002&bank_code=CMB&lBank_no=308452025026" +
                "&lBank_name=招商银行股份有限公司青岛山东路支行&bank_type=308&message_code=000000&mac=15af3e9397d5d9c441983a08a5222af8";

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
        return null;
    }

    @Override
    public long countAccount(AccountInfoBean accountInfoBean) {
        return 0;
    }

    @Override
    public AccountInfoBean getAccountDetail(long accountId) {
        return null;
    }

    @Override
    public void disableAccount(long accountId) {

    }

    @Override
    public void enableAccount(long accountId) {

    }

}
