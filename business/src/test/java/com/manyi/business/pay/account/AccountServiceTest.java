package com.manyi.business.pay.account;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.account.support.AccountServiceImpl;
import com.manyi.business.pay.account.support.AccountServiceImplTest;
import com.manyi.business.pay.account.support.dao.AccountDao;
import com.manyi.business.pay.account.support.entity.Account;
import com.manyi.business.pay.common.DealParamStr;
import com.manyi.business.pay.common.constant.BusinessType;
import com.manyi.common.util.DataUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author：WangPengfei
 * Review：
 * Date：2015/6/17 0017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-pay-junit.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AccountServiceTest {

    private static final long USERID = 131;

    private static final long ACCOUNTID = 25;

    @Autowired
    AccountServiceImplTest accountService;
    @Autowired
    AccountServiceImpl accountService1;

    /**
     * 查询联行号并更新银行账户信息
     */
    @Test
    public void lBankNoQueryAndUpdateInfoTest() {
        AccountInfoBean accountInfoBean = new AccountInfoBean();

        accountService.LBankNoQueryAndUpdateInfo(accountInfoBean, ACCOUNTID);
    }

    /**
     * 查询开户行
     */
    @Test
    public void LBankNoTest() throws Exception {

        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setBankCode("ICBC");
        accountInfoBean.setProvNo("15");

        Map<String, String> respMap = accountService.LBankNoQuery(accountInfoBean);

        assertEquals("000000", respMap.get("message_code"));
    }

    /**
     * 用*遮掩字符串
     * 银行卡、身份证号，除了后四位，其他用*来显示
     * @param string 需要处理的字符串
     * @param beginHiddenIndex 字符串起始需要隐藏的位数
     * @param endHiddenIndex 字符串末端需要隐藏的位数
     * @return
     */
    public String coverHiddenValue(String string, int beginHiddenIndex, int endHiddenIndex) {
        String beforeStr = "";
        String otherStr = "";
        if(beginHiddenIndex > 0) {
            beforeStr = string.substring(0, beginHiddenIndex);
            otherStr = string.substring(endHiddenIndex, string.length()-endHiddenIndex);
        } else {
            otherStr = string.substring(endHiddenIndex, string.length()-endHiddenIndex);
        }
        String afterStr = string.substring(string.length()-endHiddenIndex, string.length());

        String str = "";
        for (int i = 0; i < otherStr.length(); i++) {
            str += "*";
        }
        return beforeStr + str + afterStr;
    }

    /**
     * 获取hash后的字符串
     */
    @Test
    public void getMd5String() {
        String mac = "756ca4d4ad22604d83d3cd1e46ff1ec6";
        String string = "028000531000500027103450600DC22BC1A03FB2F93DF88E103080000000000";
        String sss = "8000531000500021001135520123790001该签约对象已签约";
        String str = "800053100050002CMB308452025026招商银行股份有限公司青岛山东路支行308000000";
        System.out.println(DealParamStr.buildXLSignature(string));

        String idCard = "130107199210132133";
        String cardNo = "6221501000001767516";
        System.out.println(coverHiddenValue(idCard, 5, 4));
        System.out.println(coverHiddenValue(cardNo, 0, 4));
    }

    /**
     * 获取账户信息
     */
    @Test
    public void getAccountInfoTest() {
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setUserId(USERID);
        try {
            AccountInfoBean accountInfBean = accountService.getAccountInfo(accountInfoBean);
            assertNotNull(accountInfBean);
        } catch(BusinessException e) {
            assertEquals(Type.ACC_NOT_EXIST, e.getErrorType());
        }
    }

    /**
     * 获取卡片所属银行
     * @return
     */
    @Test
    public void getBankNameTest() throws BusinessException {
        try {
            AccountInfoBean accountInfoBean = new AccountInfoBean();

            String cardNo = "6228480258267392078";
            accountInfoBean.setCardNo(cardNo);

            String bankName = accountService.getBankName(accountInfoBean);

            assertEquals("建设银行", bankName);
        } catch (BusinessException ex) {
            assertEquals(Type.SYSTEM_ERROR, ex.getErrorType());
        }
    }

    /**
     * 发送短信码
     *
     * @throws Exception
     */
    @Test
    @Rollback(false)
    public void sendMessageCodeTest() {

        AccountInfoBean accountInfoBean = new AccountInfoBean();

        accountInfoBean.setMobile("13552012379");

//        try {
//            boolean res = accountService.sendMessageCode(accountInfoBean);
//            assertEquals(true, res);
//        } catch(BusinessException e) {
//            assertEquals(Type.SEND_CODE_FAIL, e.getErrorType());
//        } catch(Exception e) {
//            System.err.println(e.getMessage());
//        }
    }

    /**
     * 实名认证
     */
    @Test
    public void authRealInfoTest() throws BusinessException {

        AccountInfoBean accountInfoBean = new AccountInfoBean();

        accountInfoBean.setMobile("13552012379");
        accountInfoBean.setVerifyCode("834604");
        accountInfoBean.setUserId(87);
        accountInfoBean.setUserName("13552012379");
        accountInfoBean.setRealName("飞总");
        accountInfoBean.setIdCardNo("130107199210132133");
        accountInfoBean.setCardNo("6221501000001767516");
        accountInfoBean.setBalance(new BigDecimal(0));
        accountInfoBean.setCardType(0);
        accountInfoBean.setUserName("13552012379");
        accountInfoBean.setBankName("招商银行");
        accountInfoBean.setBankBranch("北京");

        try {
            boolean res = accountService.authRealInfo(accountInfoBean);
            assertEquals(true, res);
        } catch(BusinessException e) {
            assertEquals(Type.CODE_CHECK_FAIL, e.getErrorType());
        }
    }

    /**
     * 修改实名手机信息
     */
    @Test
    public void updateRealMobileInfoTest() throws BusinessException {
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setUserId(88);
        accountInfoBean.setUserName("13552012379");
        accountInfoBean.setMobile("13552012379");
        accountInfoBean.setVerifyCode("834604");

        try {
            boolean res = accountService.updateRealMobileInfo(accountInfoBean);
            assertEquals(true, res);
        } catch(BusinessException e) {
            assertEquals(Type.CODE_CHECK_FAIL, e.getErrorType());
        }
    }

    /**
     * 修改实名银行卡信息
     */
    @Test
    public void updateRealBankCardInfoTest() throws BusinessException {
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setVerifyCode("834604");
        accountInfoBean.setUserId(88);
        accountInfoBean.setUserName("13552012379");
        accountInfoBean.setRealName("飞总");
        accountInfoBean.setCardNo("6221501000001767516");
        accountInfoBean.setMobile("13552012379");
        accountInfoBean.setBalance(new BigDecimal(0));
        accountInfoBean.setCardType(0);
        accountInfoBean.setBankName("建设银行");
        accountInfoBean.setBankBranch("北京");
        try {
            boolean res = accountService.updateRealBankCardInfo(accountInfoBean);
            assertEquals(true, res);
        } catch(BusinessException e) {
            assertEquals(Type.CODE_CHECK_FAIL, e.getErrorType());
        }
    }

    /**
     * 查询所有账户
     * @return
     */
    @Test
    public void queryAccountTest() {
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountInfoBean.setUserName("18654517708");
        List<AccountInfoBean> list = accountService1.queryAccount(accountInfoBean);
        assertEquals(true, list.size() > 0);
    }

    /**
     * 统计账户(按照条件)
     * @return
     */
    @Test
    public void countAccountTest() {
        AccountInfoBean accountInfoBean = new AccountInfoBean();
        accountService1.countAccount(accountInfoBean);
    }

    /**
     * 获取账户详情
     */
    @Test
    public void getAccountDetail() {
        AccountInfoBean infoBean = accountService1.getAccountDetail(ACCOUNTID);
        assertNotNull(infoBean);
    }

    /**
     * 修改账户状态
     */
    @Test
    public void disableAccount() {
        accountService1.disableAccount(ACCOUNTID);
    }

    /**
     * 修改账户状态
     */
    @Test
    public void enableAccount() {
        accountService1.enableAccount(ACCOUNTID);
    }
}
