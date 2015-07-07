package com.manyi.webext.pay.payment;

import com.manyi.base.entity.State;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.pay.payment.PayService;
import com.manyi.business.pay.payment.bean.PaymentBean;
import com.manyi.business.pay.payment.bean.ReqPaymentBean;
import com.manyi.common.bean.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 支付Server
 * @author WangPengfei
 * @reviewer
 * @version 1.0.0 2015-06-11
 */
@Controller
@RequestMapping("pay/payment")
public class PayController {

    private Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    @Qualifier(value = "payService")
    PayService payService;

    /**
     * 支付请求
     */
    @RequestMapping("/commit")
    @ResponseBody
    public ResponseBean payRequest(@RequestBody PaymentBean paymentBean) throws BusinessException {

        ReqPaymentBean reqPaymentBean = payService.payRequest(paymentBean);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        responseBean.setBody(reqPaymentBean);

        return responseBean;
    }

    /**
     * 支付回调
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String payCallback(HttpServletRequest request) throws BusinessException {

        boolean res = payService.payCallback(request);
        if(res) {
            return "success";
        }
        return "false";
    }

    /**
     * 支付功能模拟(模拟信联)
     */
    @RequestMapping("/topay")
    @ResponseBody
    public ResponseBean testpay(@RequestBody ReqPaymentBean reqPaymentBean) {
        String respStr = payService.commitPayment(reqPaymentBean);

        ResponseBean responseBean = new ResponseBean();
        responseBean.setState(State.SUCCESS.getString());
        responseBean.setBody(respStr);

        return responseBean;
    }

}
