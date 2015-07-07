package com.manyi.web.pay.account;

import com.manyi.base.entity.State;
import com.manyi.base.exception.BusinessException;
import com.manyi.bean.JsonResult;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 支付管理类
 * @author WangPengfei
 * @reviewer
 * @version 1.0.0 2015-06-11
 */
@Controller
@RequestMapping("pay/account")
public class AccManageController {

    private Logger logger = LoggerFactory.getLogger(AccManageController.class);

    @Autowired
    @Qualifier(value = "accountService")
    private AccountService accountService;

    /**
     * 查询账户
     */
    @RequestMapping("/queryAccByCondition")
    @ResponseBody
    public Map<String, Object> queryAccByCondition(@RequestBody AccountInfoBean accountInfoBean) throws BusinessException {

        List<AccountInfoBean> accountList = accountService.queryAccount(accountInfoBean);
        long totalCount = accountService.countAccount(accountInfoBean);

        Map resultMap = new HashMap();
        resultMap.put("list", accountList);
        resultMap.put("totalCount", totalCount);

        return resultMap;
    }

//    /**
//     * 查询账户
//     */
//    @RequestMapping("/queryAccount")
//    @ResponseBody
//    public Map<String, Object> queryAccount(AccountInfoBean accountInfoBean) throws BusinessException {
//        List<AccountInfoBean> accountInfoBeans = accountService.queryAccount(accountInfoBean);
//        List<AccountInfoBean> accountList = accountInfoBeans;
//        long totalCount = accountService.countAccount(accountInfoBean);
//
//        Map resultMap = new HashMap();
//        resultMap.put("list", accountList);
//        resultMap.put("totalCount", totalCount);
//
//        return resultMap;
//    }

    /**
     * 获取账户详情
     */
    @RequestMapping("/getAccountDetail")
    @ResponseBody
    public AccountInfoBean getAccountDetail(@RequestBody long accountId) throws BusinessException {

        AccountInfoBean accountInfoBean = accountService.getAccountDetail(accountId);

        return accountInfoBean;
    }


    /**
     * 冻结账户
     */
    @RequestMapping("/disableAccount")
    @ResponseBody
    public JsonResult disableAccount(@RequestBody long accountId) throws BusinessException {

        accountService.disableAccount(accountId);

        return new JsonResult(JsonResult.SUCCESS, "");
    }

    /**
     * 解冻账户
     */
    @RequestMapping("/enableAccount")
    @ResponseBody
    public JsonResult enableAccount(@RequestBody long accountId) throws BusinessException {

        accountService.enableAccount(accountId);

        return new JsonResult(JsonResult.SUCCESS, "");
    }
}
