package com.manyi.webext.pay.account;

import com.manyi.base.entity.State;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.common.bean.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 支付Server
 * @author WangPengfei
 * @reviewer
 * @version 1.0.0 2015/5/18
 */
@Controller
@RequestMapping("pay/account")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    @Qualifier(value = "accountService")
    private AccountService accountService;

    /**
     * 是否需要开通钱包验证
     */
    @RequestMapping("/getAccountInfo")
    @ResponseBody
    public ResponseBean getAccountInfo(@RequestBody AccountInfoBean accountIf) throws BusinessException {

        AccountInfoBean accountInfoBean = accountService.getAccountInfo(accountIf);

        // 处理重要信息，银行卡号、身份证号
        accountInfoBean.setCardNo(coverHiddenValue(accountInfoBean.getCardNo(), 0, 4));
        accountInfoBean.setIdCardNo(coverHiddenValue(accountInfoBean.getIdCardNo(), 4, 4));

        ResponseBean responseBean = new ResponseBean();

        responseBean.setBody(accountInfoBean);
        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
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
     * 获取卡片所属银行
     */
    @RequestMapping("/getBankName")
    @ResponseBody
    public ResponseBean getBankName(@RequestBody AccountInfoBean accountInfoBean) throws BusinessException {

        String bankName = accountService.getBankName(accountInfoBean);

        ResponseBean responseBean = new ResponseBean();

        responseBean.setBody(bankName);
        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
    }

    /**
     * 用户注册发送验证码
     * @param accountInfoBean
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendMessageCode")
    @ResponseBody
    public ResponseBean sendMsgCode(@RequestBody AccountInfoBean accountInfoBean) throws Exception {

        accountService.sendMessageCode(accountInfoBean);

        ResponseBean responseBean = new ResponseBean();

        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
    }

    /**
     * 实名认证
     */
    @RequestMapping("/checkRealName")
    @ResponseBody
    public ResponseBean checkRealName(@RequestBody AccountInfoBean accountInfoBean) throws BusinessException {

        accountService.authRealInfo(accountInfoBean);

        ResponseBean responseBean = new ResponseBean();

        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
    }

    /**
     * 修改实名手机信息
     */
    @RequestMapping("/updateMobileInfo")
    @ResponseBody
    public ResponseBean updateRealMobileInfo(@RequestBody AccountInfoBean accountInfoBean) throws BusinessException {

        accountService.updateRealMobileInfo(accountInfoBean);

        ResponseBean responseBean = new ResponseBean();

        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
    }

    /**
     * 修改实名银行卡信息
     */
    @RequestMapping("/updateBankCardInfo")
    @ResponseBody
    public ResponseBean updateRealBankCardInfo(@RequestBody AccountInfoBean accountInfoBean) throws BusinessException {

        accountService.updateRealBankCardInfo(accountInfoBean);

        ResponseBean responseBean = new ResponseBean();

        responseBean.setState(State.SUCCESS.getString());

        return responseBean;
    }

}
