package com.manyi.webext.pay.bill;

import com.manyi.base.entity.State;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.bill.BillService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.business.pay.bill.support.entity.Bill;
import com.manyi.common.bean.response.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/15 0015,15:11.
 * @Description:
 * @version: 1.0.0
 * @reviewer:
 */
@Controller
@RequestMapping("pay/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("/findBill")
    @ResponseBody
    public ResponseBean payRequest(@RequestBody BillBean billBean) throws BusinessException {
        ResponseBean responseBean = new ResponseBean();
        List<Bill> billList = billService.findBill(billBean);
        responseBean.setState(State.SUCCESS.getString());
        responseBean.setBody(billList);
        return responseBean;
    }
}
