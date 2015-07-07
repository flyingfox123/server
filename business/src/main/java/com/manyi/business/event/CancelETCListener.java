package com.manyi.business.event;

import com.manyi.business.etc.support.dao.EtcDao;
import com.manyi.business.order.bean.BusinessType;
import com.manyi.business.order.bean.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
@Component
public class CancelETCListener implements ApplicationListener<CancelOrderEvent> {

    @Autowired
    private EtcDao etcDao;

    @Async
    @Override
    public void onApplicationEvent(final CancelOrderEvent event) {
        OrderItem item = (OrderItem) event.getSource();
        if(item.getType().equals(BusinessType.ETC))
        {
            etcDao.deleteETC(item.getId());
        }
        else if (item.getType().equals(BusinessType.ETC_INVOICE))
        {
            etcDao.deleteETCInvoice(item.getId());
        }


    }
}
