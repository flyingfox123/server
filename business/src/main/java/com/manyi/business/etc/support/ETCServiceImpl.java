package com.manyi.business.etc.support;

import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import com.google.gson.Gson;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.etc.ETCService;
import com.manyi.business.etc.bean.*;
import com.manyi.business.etc.support.dao.EtcDao;
import com.manyi.business.event.OrderSuccessEvent;
import com.manyi.business.order.OrderService;
import com.manyi.business.order.bean.*;
import com.manyi.business.order.support.dao.OrderDao;
import com.manyi.business.pay.bill.BillOrderService;
import com.manyi.business.pay.bill.bean.BillBean;
import com.manyi.common.util.DateUtil;
import com.manyi.common.util.NumberValidationUtils;
import com.manyi.usercenter.user.UserService;
import com.manyi.usercenter.user.bean.IndividualBean;
import com.manyi.usercenter.user.bean.PlatUser;
import com.manyi.usercenter.user.support.entity.Individual;
import com.manyi.usercenter.user.support.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */

@Service("ETCService")
public class ETCServiceImpl implements ETCService
{

    @Autowired
    private EtcDao etcDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BillOrderService billOrderService;


    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private UserService userService;


    @Autowired
    private OrderService orderService;

    Gson gson = new Gson();

    public static final int SUCCESS_CODE = 0 ;

    @Override
    public OrderItem createETCOrder(Etc etc) throws BusinessException {

            if(StringUtils.isEmpty(etc) || !etc.isObjectLegal())
            {
                throw new BusinessException(Type.PARAM_ERROR);
            }
            EtcDescription description = new EtcDescription();
            description.setPlateNum(etc.getPlateNum());
            description.setETCCode(etc.getETCCode());
            etc.setDescription(gson.toJson(description));
            etc.setType(BusinessType.ETC);
            etc.setState(OrderItemStatus.PENDING);
            etc.setCreateTime(DateUtil.getCurrentString(new Date()));
            orderDao.createOrderItem(etc);
            etc.setOrderItemId(etc.getId());
            etcDao.addETC(etc);
            return etc;
    }



    /**
     *  ETC计数
     * @param info
     */
        private void addETCCount(Etc info) {
        Integer count = 0 ;
        count = etcDao.getETCCurrentCount(info.getUserId(),info.getETCCode());
        if(count == 0 )
        {
            etcDao.createETCCount(info);
        }
        else
        {
            etcDao.addETCCount(info.getUserId(),info.getETCCode());
        }
      }

    @Override
    public OrderItem createETCInvoiceOrder(EtcInvoice info) throws BusinessException {

        if (StringUtils.isEmpty(info) ||  !info.isObjectLegal())
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
        des.setAddressee(info.getAddressee());
        info.setDescription(gson.toJson(des));
        info.setType(BusinessType.ETC_INVOICE);
        info.setState(OrderItemStatus.PENDING);
        info.setCreateTime(DateUtil.getCurrentString(new Date()));
        orderDao.createOrderItem(info);
        info.setOrderItemId(info.getId());
        etcDao.addETCInvoice(info);
        return info;
    }

    @Override
    public void preChargeETCCallBack(ETCPreChargeRequest request) throws BusinessException, APIConnectionException, APIRequestException {

         if(StringUtils.isEmpty(request) || !request.isObjectLegal())
         {
             throw new BusinessException(Type.PARAM_ERROR);
         }
         Order order = orderDao.getOrderBySeq(request.getOrderSeq()) ;
         if(StringUtils.isEmpty(order) )
         {
            throw new BusinessException(Type.PARAM_ERROR);
         }
         finishEtcItem(order,request);
         finishEtcOrder(order);

    }

    /*
      结束ETC预充值子订单
     */
    private void finishEtcItem(Order order, ETCPreChargeRequest request) throws BusinessException {

        for(EtcResult res : request.getList())
        {
            OrderItem item = orderDao.getOrderItemBySeq(res.getOrderItemSeq());
            if(!checkEtcReqItem(item,order))
            {
                throw new BusinessException(Type.PARAM_ERROR);
            }
            if(item.getType().equals(BusinessType.ETC) && res.getState().intValue() == SUCCESS_CODE)
            {
                item.setState(OrderItemStatus.SUCCESS);
                orderDao.updateOrderItem(item);
                Etc etc = etcDao.queryEtcOrderItem(item.getId());
                if(StringUtils.isEmpty(etc))
                {
                    throw new BusinessException(Type.PARAM_ERROR);
                }
                etc.setUserId(item.getUserId());
                addETCCount(etc);
            }
        }
    }

    /*
      检查ETC参数
     */
    private boolean checkEtcReqItem(OrderItem item , Order order) {

        if(StringUtils.isEmpty(item)  || item.getOrderId() != order.getId() ||!item.getState().equals(OrderItemStatus.PAYED))
        {
          return false;
        }
        else
        {
            return true;
        }

    }

    /**
     * 结束ETC订单，下发事件
     * @param order
     */
    private void finishEtcOrder(Order order) {

        final List<OrderItem> itemList = orderDao.queryItemByOrder(order.getId());
        for(OrderItem item : itemList)
        {
            if(item.getType().equals(BusinessType.ETC) &&!item.getState().equals(OrderItemStatus.SUCCESS))
            {
                return;
            }
        }
        order.setState(OrderStatus.SUCCESS);
        order.setItemList(itemList);
        orderDao.updateOrder(order);
        applicationContext.publishEvent(new OrderSuccessEvent(order));
    }

    @Override
    public void EtcInvoiceCallBack(EtcResult callBack) throws BusinessException {

        if(StringUtils.isEmpty(callBack.getOrderItemSeq()) ||StringUtils.isEmpty( callBack.getState()))
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        OrderItem item = orderDao.getOrderItemBySeq(callBack.getOrderItemSeq());

        if(StringUtils.isEmpty(item))
        {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        if(callBack.getState().intValue() == SUCCESS_CODE)
        {
            item.setState(OrderItemStatus.SUCCESS);
            orderDao.updateOrderItem(item);
        }

        else
        {
            item.setState(OrderItemStatus.FAILED);
            orderDao.updateOrderItem(item);
        }


    }

    @Override
    public List<EtcDescription> queryETCChargeHistory(long userId) {
        List<EtcDescription> list =  etcDao.queryUserEtcCount(userId) ;
        return list;
    }

    @Override
    public EtcOrderAmountResult queryEtcOrderAmount(long userId) throws BusinessException {

        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+ 1;
        double monthAmount =   orderService.queryUserOrderAmountMonthly(month , userId , OrderType.ETC_TYPE);
        double yearAmount =   orderService.queryUserOrderAmountYearly(year, userId, OrderType.ETC_TYPE);
        EtcOrderAmountResult result = new EtcOrderAmountResult();
        result.setMonthAmount(monthAmount);
        result.setYearAmount(yearAmount);
        PlatUser platUser = new PlatUser();
        platUser.setId(userId);
        UserInfo userInfo =  userService.getUserAndVehicleInfo(platUser);
        IndividualBean individualBean = new IndividualBean();
        individualBean.setUserId(userId);
        individualBean.setPhone("");
        List<Individual>  list = userService.findIndividual(individualBean);
        if(!StringUtils.isEmpty(userInfo)&& !StringUtils.isEmpty(userInfo.getVehicle()))
        {
            result.setPlateNum(userInfo.getVehicle().getPlateNo());
            result.setETCCode(userInfo.getVehicle().getLuCard());
            if(!StringUtils.isEmpty(list) && list.size()>0)
            {
                result.setDriverName(list.get(0).getDriverName());
                result.setUrl(list.get(0).getHeadPic());
            }

        }
        else
        {
            result.setPlateNum(null);
            result.setETCCode(null);
        }
        return result;
    }

    @Override
    public void etcPayCallBack(String orderSeqNo, String amount, int payChannel, int state) throws BusinessException {

        if (StringUtils.isEmpty(orderSeqNo) || (payChannel != PayChannel.BANK_CHANNEL && payChannel != PayChannel.XL_CHANNEL)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }
        if (!NumberValidationUtils.isRealNumber(amount)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        Order order = orderDao.getOrderBySeq(orderSeqNo);

        if (StringUtils.isEmpty(order)) {
            throw new BusinessException(Type.PARAM_ERROR);
        }

        if (!order.getState().equals(OrderStatus.PENDING)) {
            throw new BusinessException(Type.ORDER_STATUS_ERROR);
        }
        updatePayedOrder(amount, payChannel,state,order);
        BillBean bean = new BillBean();
        bean.setOrderNo(orderSeqNo);
        billOrderService.createEtcBill(bean);
    }

    private void updatePayedOrder(String amount, int payChannel, int state, Order order) throws BusinessException {

        if(state != SUCCESS_CODE)
        {
            order.setState(OrderStatus.PAY_FAILED);
            order.setPayChannel(payChannel);
            orderDao.updateOrder(order);

        }
        BigDecimal value = new BigDecimal(amount);
        if (value.compareTo(order.getPaidAmount()) == 0) {
            order.setState(OrderStatus.PAYED);
            order.setPayChannel(payChannel);
            orderDao.updateOrder(order);
            List<OrderItem> list = orderDao.queryItemByOrder(order.getId());
            for (OrderItem item : list) {
                item.setState(OrderItemStatus.PAYED);
                orderDao.updateOrderItem(item);
            }
        } else {
            throw new BusinessException(Type.PARAM_ERROR);
        }
    }

    @Override
    public List<Etc> queryETCItems(QueryOrderItemCondition condition) {
        List<Etc> list =  etcDao.queryEtcItems(condition.getOrderId());
        return list;
    }


}

