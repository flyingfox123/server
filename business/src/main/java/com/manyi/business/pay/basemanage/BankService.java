package com.manyi.business.pay.basemanage;

import com.manyi.business.pay.basemanage.bean.BankInfoBean;
import com.manyi.business.pay.basemanage.support.entity.BankInfo;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-17
 * @reviewer
 */
public interface BankService {
    List<BankInfo> getBakInfoAll();
}
