package com.manyi.webext.order;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.manyi.base.exception.BusinessException;
import com.manyi.bean.JsonResult;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.*;
import com.manyi.business.order.OrderService;
import com.manyi.webext.order.bean.Test;
import com.manyi.webext.order.bean.Test1;
import com.manyi.webext.order.service.TestOrderService;
import com.manyi.business.order.bean.*;
import com.manyi.common.bean.response.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
@RequestMapping("/order")
public class TestOrderController {

    @Autowired
    private  OrderService  orderService;



    @Autowired
    private TestOrderService testOrderService;

    @Autowired
    private ETCService etcService;
    /**
     *查询订单各种状态的数量
     *
     * @return
     */
    @RequestMapping("/testETC")
    @ResponseBody
    public ResponseBean  testETC (@RequestBody Test request) throws BusinessException, APIConnectionException, APIRequestException {
        etcService.etcPayCallBack(request.getTest(), request.getAmount(), PayChannel.XL_CHANNEL, 0);
        ETCPreChargeRequest request1 = new ETCPreChargeRequest();
        List<EtcResult> backlist = new ArrayList<EtcResult>();
        for(Test1 result : request.getList())
        {
            EtcResult result1 = new EtcResult();
            result1.setOrderItemSeq(result.getTest1());
            result1.setState(0);
            backlist.add(result1);
        }
        request1.setOrderSeq(request.getTest());
        request1.setState(0);
        request1.setList(backlist);
        request1.setAmount(request.getAmount());
        etcService.preChargeETCCallBack(request1);
        ResponseBean response = new ResponseBean();
        response.setState(JsonResult.SUCCESS);
        return response;
    }


    /**
     * 下单
     *
     * @return
     */
    @RequestMapping("/testSubscribe")
    @ResponseBody
    public ResponseBean testSubscribe(@RequestBody OrderBean req) throws IOException, BusinessException {

        Order order = testOrderService.subscribeTest(req) ;
        ResponseBean response = new ResponseBean();
        response.setBody(order);
        response.setState(JsonResult.SUCCESS);
        return  response;
    }


    /**
     * 模拟司机确认支付
     *
     * @return
     */
    @RequestMapping("/payForDriver")
    @ResponseBody
    public ResponseBean payForDriver(@RequestBody Test request) throws IOException, BusinessException {

        testOrderService.payForDriver(request);
        ResponseBean response = new ResponseBean();
        response.setState(JsonResult.SUCCESS);
        return  response;
    }



    /**
     * 模拟司机确认支付
     *
     * @return
     */
    @RequestMapping("/refundOrder")
    @ResponseBody
    public ResponseBean refundOrder(@RequestBody Order request) throws IOException, BusinessException {

        testOrderService.refundOrder(request);
        ResponseBean response = new ResponseBean();
        response.setState(JsonResult.SUCCESS);
        return  response;
    }



}
