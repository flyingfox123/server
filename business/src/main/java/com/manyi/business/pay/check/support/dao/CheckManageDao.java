package com.manyi.business.pay.check.support.dao;

import com.manyi.business.pay.check.bean.CheckDetailBean;
import com.manyi.business.pay.check.support.entity.CheckDetail;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/25 0025,22:33.
 * @Description:
 * @reviewer:
 */
public interface CheckManageDao {

    /**
     * 按照日期，对账状态查询每天各个状态的总数量
     * @param checkDetailBean
     * @return
     */
    List<CheckDetail> getSumGroupByDay(CheckDetailBean checkDetailBean);

    /**
     * 根据条件查询对账详情信息
     * @param checkDetailBean
     * @return
     */
    List<CheckDetail> getCheckDetail(CheckDetailBean checkDetailBean);
}
