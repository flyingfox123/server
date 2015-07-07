package com.manyi.webext.order;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.manyi.base.exception.BusinessException;
import com.manyi.bean.JsonResult;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.*;
import com.manyi.common.bean.response.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
@RequestMapping("/etcEXT")
public class EtcExtController {


    private  static  String OK ="200" ;


    @Autowired
    private ETCService etcService;

    /**
     *支付回调接口（圈存后台）
     *
     * @return
     */
    @RequestMapping("/payETCCallBack")
    @ResponseBody
    public ResponseBean  testETC (@RequestBody EtcPayRequest request) throws BusinessException, APIConnectionException, APIRequestException {
        etcService.etcPayCallBack(request.getOrderSeqNo(),request.getAmount(),request.getPayChannel(),request.getState());
        ResponseBean response = new ResponseBean();
        response.setErrCode(OK);
        response.setErrMsg(JsonResult.SUCCESS);
        return response;
    }


    /**
     *支付回调接口（圈存后台）
     *
     * @return
     */
    @RequestMapping("/preChargeETCCallBack")
    @ResponseBody
    public ResponseBean  preChargeETCCallBack (@RequestBody ETCPreChargeRequest request) throws BusinessException, APIConnectionException, APIRequestException {
        etcService.preChargeETCCallBack(request);
        ResponseBean response = new ResponseBean();
        response.setErrCode(OK);
        response.setErrMsg(JsonResult.SUCCESS);
        return response;
    }


}
