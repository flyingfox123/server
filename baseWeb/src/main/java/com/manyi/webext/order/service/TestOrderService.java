package com.manyi.webext.order.service;

import com.manyi.base.exception.BusinessException;
import com.manyi.business.order.bean.Order;
import com.manyi.business.order.bean.OrderBean;
import com.manyi.webext.order.bean.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2015/6/16.
 */
public interface TestOrderService {

    /**
     * 下单
     * @param request
     */
    Order subscribeTest(OrderBean request) throws BusinessException, IOException;


    /**
     * 下单
     * @param request
     */
    void payForDriver(Test request) throws BusinessException, IOException;


    /**
     * 下单
     * @param request
     */
    void refundOrder(Order request) throws BusinessException, IOException;

}
