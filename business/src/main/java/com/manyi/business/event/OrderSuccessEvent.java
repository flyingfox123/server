package com.manyi.business.event;

import com.manyi.business.order.bean.Order;
import org.springframework.context.ApplicationEvent;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class OrderSuccessEvent extends ApplicationEvent {

    public OrderSuccessEvent(Order order) {
        super(order);
    }

}
