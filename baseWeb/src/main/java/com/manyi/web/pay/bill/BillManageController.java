package com.manyi.web.pay.bill;

import com.manyi.business.pay.account.AccountService;
import com.manyi.business.pay.account.bean.AccountInfoBean;
import com.manyi.business.pay.bill.BillForManageService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.business.pay.bill.support.entity.BillPage;
import com.manyi.common.param.ParamService;
import com.manyi.common.param.support.entity.Param;
import com.manyi.common.util.Constant.ParamName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,15:18.
 * @Description:
 * @reviewer:
 */
@Controller
@RequestMapping("pay/bill")
public class BillManageController {

    @Autowired
    private ParamService paramService;

    @Autowired
    private BillForManageService billForManageService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/manage/getTradeState")
    @ResponseBody
    public List<Param> getTradeStateList(){
        List<Param> paramList = paramService.getParamList(ParamName.TRADESTATE);
        return paramList;
    }

    @RequestMapping("/manage/getBill")
    @ResponseBody
    public BillPage getBill(BillBean billBean){
        BillPage billPage = new BillPage();
        List<Bill> billList = billForManageService.findBill(billBean);
        billPage.setList(billList);
//        int count = billForManageService.getBillCount(billBean);
//        int pageCount = (count + billBean.getPageSize() - 1) / billBean.getPageSize();
//        billPage.setPageCount(pageCount);
        return billPage;
    }

    @RequestMapping("/manage/getAccount")
    @ResponseBody
    public AccountInfoBean getAccount(Long accountId){
        AccountInfoBean accountInfoBean = accountService.getAccountDetail(accountId);
        return accountInfoBean;
    }
}
