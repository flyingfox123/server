package com.manyi.business.etc.support;

import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.*;
import com.manyi.business.etc.support.dao.EtcDao;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2015/5/11.
 */
@Service("ETCService")
public class ETCServiceImpl implements ETCService
{

    @Autowired
    private EtcDao etcDao;

    @Autowired
    private OrderDao orderDao;

    Gson gson = new Gson();

    @Override
    public void createETCOrder(List<Etc> list) throws BusinessException {
        if(null == list){
            throw new BusinessException(Type.PARAM_ERROR);
        }
        for(Etc info : list)
        {
            if(null == info.getSeqNo() ||
                    null == info.getETCCode() ||
                      null == info.getPlateNum()||
                         null ==info.getPayableAmount()||
                          0 == info.getUserId()||
                           0==info.getOrderId())
            {
                throw new BusinessException(Type.PARAM_ERROR);
            }

            EtcDescription description = new EtcDescription();
            description.setPlateNum(info.getPlateNum());
            description.setETCCode(info.getETCCode());
            info.setDescription(gson.toJson(description));
            info.setType(BusinessType.ETC);
            info.setState(OrderItemStatus.PENDING);
            orderDao.creatOrderItem(info);
            info.setOrderItemId(info.getId());
            etcDao.addETC(info);
        }
    }

    @Override
    public void createETCInvoiceOrder(EtcInvoice info) throws BusinessException {

        if(null == info.getAmount() ||
                null == info.getPhone()
                || null == info.getInvoiceHeader()
                || null == info.getPostAddress()
                ||null == info.getSeqNo()
                ||null ==info.getPayableAmount() ||
                0 == info.getUserId()||
                0==info.getOrderId())
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderById(info.getOrderId());
        if(info.getAmount().intValue() != order.getPaidAmount().intValue())
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        EtcInvoiceDes des = new EtcInvoiceDes();
        des.setAmount(info.getAmount());
        des.setInvoiceHeader(info.getInvoiceHeader());
        des.setPhone(info.getPhone());
        des.setPostAddress(info.getPostAddress());
        info.setDescription(gson.toJson(des));
        info.setType(BusinessType.ETC_INVOICE);
        info.setState(OrderItemStatus.PENDING);
        orderDao.creatOrderItem(info);

        info.setOrderItemId(info.getId());
        etcDao.addETCInvoice(info);


    }



    @Override
    public void preChargeETCCallBack(ETCPreChargeRequest request) throws BusinessException {

        checkParam(request) ;
        Order order = orderDao.getOrderBySeq(request.getOrderSeq()) ;
        if(null == order )
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        for(EtcResult res : request.getList())
        {
           OrderItem item = orderDao.getOrderItemBySeq(res.getOrderItemSeq());
            if(item.getOrderId() != order.getId() ||!item.getState().equals(OrderItemStatus.PAYED))
            {
                throw new BusinessException(Type.PARAM_ERROR);
            }
            if(res.getState().equals("success") )
            {
                  item.setState(OrderItemStatus.SUCCESS);
                  orderDao.updateOrderItem(item);
            }
        }

        List<OrderItem> itemList = orderDao.queryItemByOrder(order.getId());

        for(OrderItem item : itemList)
        {
            if(item.getType().equals(BusinessType.ETC) &&!item.getState().equals(OrderItemStatus.SUCCESS))
            {
                return;
            }
        }
         order.setState(OrderStatus.SUCCESS);
         orderDao.updateOrder(order);
    }

    private void checkParam(ETCPreChargeRequest request) throws BusinessException {

        if(null == request || null == request.getState()
                || null== request.getOrderSeq() || null ==request.getList())
        {
            throw new BusinessException(Type.PARAM_ERROR);

        }
        for(EtcResult re : request.getList())
        {
                if(null == re.getOrderItemSeq() || null == re.getState())
                {
                    throw new BusinessException(Type.PARAM_ERROR);
                }
        }
    }


    @Override
    public void EtcInvoiceCallBack(EtcResult callBack) throws BusinessException {

        if(null == callBack.getOrderItemSeq() || null == callBack.getState())
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        OrderItem item = orderDao.getOrderItemBySeq(callBack.getOrderItemSeq());

        if(null == item)
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        if(callBack.getState().equals("success"))
        {
            item.setState(OrderItemStatus.SUCCESS);
            orderDao.updateOrderItem(item);
        }

        else if(callBack.getState().equals("failed"))
        {
            item.setState(OrderItemStatus.FAILED);
            orderDao.updateOrderItem(item);
        }

        else {
            throw new BusinessException(Type.PARAM_ERROR);
        }

    }
}
