package com.manyi.webext.order;


import com.manyi.base.exception.BusinessException;
import com.manyi.bean.ETCRequest;
import com.manyi.bean.JsonResult;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.*;
import com.manyi.business.order.OrderService;
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
public class OrderExtController {

    @Autowired
    private  OrderService  orderService;



    @Autowired
    private TestOrderService testOrderService;

    @Autowired
    private ETCService etcService;

    /**
     * 下单
     *
     * @return
     */
    @RequestMapping("/subscribe")
    @ResponseBody
    public ResponseBean subscribe(@RequestBody OrderBean req) throws IOException, BusinessException {

        Order order = orderService.subscribe( req) ;
        ResponseBean response = new ResponseBean();
        response.setBody(order);
        response.setState(JsonResult.SUCCESS);
        return  response;
    }

    /**
     * 取消订单
     *
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public ResponseBean cancelOrder (@RequestBody QueryOrderCondition condition) throws BusinessException {

        orderService.cancelOrder(condition.getSeqNo());
        ResponseBean response = new ResponseBean();
        response.setState(JsonResult.SUCCESS);
        return  response;
    }


    /**
     *查询订单
     *
     * @return
     */
    @RequestMapping("/queryOrder")
    @ResponseBody
    public ResponseBean queryOrder (@RequestBody QueryOrderCondition order) throws BusinessException {

        List<Order> result =  orderService.queryOrder(order) ;
        ResponseBean response = new ResponseBean();
        response.setBody(result);
        response.setState(JsonResult.SUCCESS);
        return response;
    }


    /**
     *查询订单项
     *
     * @return
     */
    @RequestMapping("/queryOrderItem")
    @ResponseBody
    public ResponseBean  queryOrderItem (@RequestBody QueryOrderItemCondition condition) throws BusinessException {



        List<OrderItem> result =  orderService.queryOrderItem(condition);
        ResponseBean response = new ResponseBean();
        response.setBody(result);
        response.setState(JsonResult.SUCCESS);
        return response;
    }

    /**
     *查询历史记录
     *
     * @return
     */
    @RequestMapping("/queryETCRecord")
    @ResponseBody
    public ResponseBean  queryETCRecord (@RequestBody ETCRequest request) throws BusinessException {

        List<EtcDescription> list =  etcService.queryETCChargeHistory(request.getUserId());
        ResponseBean response = new ResponseBean();
        response.setBody(list);
        response.setState(JsonResult.SUCCESS);
        return response;
    }

    /**
     *查询ETC订单月、年金额
     *
     * @return
     */
    @RequestMapping("/queryEtcOrderAmount")
    @ResponseBody
    public ResponseBean  queryEtcOrderAmount (@RequestBody ETCRequest request) throws BusinessException {

        EtcOrderAmountResult result =  etcService.queryEtcOrderAmount(request.getUserId());
        ResponseBean response = new ResponseBean();
        response.setBody(result);
        response.setState(JsonResult.SUCCESS);
        return response;
    }


    /**
     *查询订单各种状态的数量
     *
     * @return
     */
    @RequestMapping("/queryOrderStatusCount")
    @ResponseBody
    public ResponseBean  queryOrderStatusCount (@RequestBody QueryOrderCondition condition) throws BusinessException {

        int totalCount = orderService.queryOrderCount(condition);
        condition.setState(OrderStatus.PENDING);
        int pendCount = orderService.queryOrderCount(condition);
        int otherCount = totalCount - pendCount ;
        int unfinishCount = 0 ;
        List<Order> unfinishedOrders=  orderService.queryUnfinishedOrder(condition);
        if(null != unfinishedOrders && unfinishedOrders.size() != 0)
        {
            unfinishCount = unfinishedOrders.size();
        }
        OrderStatusCountResult  result = new OrderStatusCountResult();
        result.setOtherCount(otherCount);

        result.setPendCount(pendCount);

        result.setTotalCount(totalCount);

        result.setUnDoCount(unfinishCount);

        ResponseBean response = new ResponseBean();
        response.setBody(result);
        response.setState(JsonResult.SUCCESS);
        return response;
    }

    /**
     *查询订单
     *
     * @return
     */
    @RequestMapping("/queryUnfinishedOrder")
    @ResponseBody
    public ResponseBean queryUnfinishedOrder (@RequestBody QueryOrderCondition order) throws BusinessException {

        List<Order> unfinishedOrders=  orderService.queryUnfinishedOrder(order);
        ResponseBean response = new ResponseBean();
        response.setBody(unfinishedOrders);
        response.setState(JsonResult.SUCCESS);
        return response;
    }




}
