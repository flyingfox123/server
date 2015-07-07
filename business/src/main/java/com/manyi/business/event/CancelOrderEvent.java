package com.manyi.business.event;

import com.manyi.business.order.bean.OrderItem;
import org.springframework.context.ApplicationEvent;

/**
 * @Description: 取消订单事件
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class CancelOrderEvent extends ApplicationEvent {

    public CancelOrderEvent(OrderItem item) {
        super(item);
    }

}
