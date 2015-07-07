package com.manyi.web.business.order;

import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.Etc;
import com.manyi.business.order.OrderService;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.IndividualBean;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.webext.order.service.TestOrderService;
import com.manyi.business.order.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private  OrderService  orderService;

    @Autowired
    private UserService userService;


    @Autowired
    private ETCService  etcService;
    /**
     * 查询订单
     *
     * @return
     */
    @RequestMapping("/sys/select")
    @ResponseBody
    public QueryOrderResult queryOrder(int page , int size ,String seq ,Long userId,String state, Integer type) {
        QueryOrderResult result = new QueryOrderResult();
        QueryOrderCondition order = new QueryOrderCondition();
        order.setPageNum(page);
        order.setPageSize(size);

        if(null == state || state.equals(""))
        {
            order.setState(null);
        }
        else
        {
            order.setState(state);
        }

        if(null == userId || userId.equals(""))
        {
            order.setUserId(0);
        }
        else
        {
            order.setUserId(userId);
        }

        if(null == type || type.equals(""))
        {
            order.setType(0);
        }
        else
        {
            order.setType(type);
        }

        if(null == seq || seq.equals(""))
        {
            order.setSeqNo(null);
        }
        else
        {
            order.setSeqNo(seq);
        }
        List<Order> list =  orderService.queryOrder(order);
        result.setList(list);
        result.setPageCount(orderService.getOrderCount(order));
        return result;
    }


    /**
     * 查询订单总数量
     *
     * @return
     */
    @RequestMapping("/sys/total")
    @ResponseBody
    public int queryOrderCount(@RequestBody QueryOrderCondition condition) {
        //  QueryOrderCondition order = new QueryOrderCondition();
        int count  = orderService.queryOrderCount(condition);
        return count;
    }


    /**
     * 查询订单项
     *
     * @return
     */
    @RequestMapping("/selectItems")
    @ResponseBody
    public List<OrderItem> queryOrderItem(long orderId) {

        QueryOrderItemCondition condition = new QueryOrderItemCondition();
        condition.setOrderId(orderId);
        return orderService.queryOrderItem(condition);
    }

    /**
     * 查询订单项
     *
     * @return
     */
    @RequestMapping("/queryOrderUser")
    @ResponseBody
    public Individual queryOrderUser(long userId) {

        IndividualBean individualBean = new IndividualBean();
        individualBean.setUserId(userId);
        individualBean.setPhone("");
        List<Individual>  list = userService.findIndividual(individualBean);
        return list.get(0);
    }



    /**
     * 查询订单项
     *
     * @return
     */
    @RequestMapping("/selectETCItems")
    @ResponseBody
    public List<Etc> selectETCItems(long orderId) {

        QueryOrderItemCondition condition = new QueryOrderItemCondition();
        condition.setOrderId(orderId);
        return etcService.queryETCItems(condition);
    }


}
