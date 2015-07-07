package com.manyi.business.etc.support.dao;

import com.manyi.business.etc.bean.Etc;
import com.manyi.business.etc.bean.EtcDescription;
import com.manyi.business.etc.bean.EtcInvoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface EtcDao {

    /**
     * 增加etc记录
     * @param info
     */
    public void addETC(Etc info);

    /**
     * 增加etc发票记录
     * @param invoice
     */
    void addETCInvoice(EtcInvoice invoice);


    int getETCCurrentCount(@Param("userId")long userId, @Param("ETCCode")String etcCode);

    void createETCCount(Etc info);

    void addETCCount(@Param("userId")long userId, @Param("ETCCode")String etcCode);

    List<EtcDescription> queryUserEtcCount(@Param("userId") long userId);

    Etc queryEtcOrderItem (@Param("orderItemId") long orderItemId)  ;

    void deleteETC(@Param("orderItemId") long orderItemId);


    void deleteETCInvoice(@Param("orderItemId") long orderItemId);

    List<Etc> queryEtcItems(@Param("orderId") long orderId);
}
