package com.manyi.business.pay.check;

import com.manyi.business.pay.check.bean.CheckDetailBean;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import com.manyi.business.pay.check.support.entity.CheckSum;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/24 0024,13:58.
 * @Description:
 * @reviewer:
 */
public interface CheckManageService {

    /**
     * 按照日期，对账状态查询每天各个状态的总数量
     * @param checkDetailBean
     * @return
     */
    List<CheckSum> getCheckSum(CheckDetailBean checkDetailBean);

    /**
     * 根据条件查询对账详情信息
     * @param checkDetailBean
     * @return
     */
    List<CheckDetail> getCheckDetail(CheckDetailBean checkDetailBean);
}
