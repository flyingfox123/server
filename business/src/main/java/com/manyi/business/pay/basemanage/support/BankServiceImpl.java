package com.manyi.business.pay.basemanage.support;

import com.manyi.business.pay.basemanage.BankService;
import com.manyi.business.pay.basemanage.bean.BankInfoBean;
import com.manyi.business.pay.basemanage.support.dao.BankInfoDao;
import com.manyi.business.pay.basemanage.support.entity.BankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author ZhangYuFeng
 * @version 1.0.0 2015-6-17
 * @reviewer
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankInfoDao bankInfoDao;

//    @Override
//    public List<BankInfo> getBankInfo(BankInfoBean bankInfoBean) {
//        return null;
//    }

    @Override
    public List<BankInfo> getBakInfoAll() {
        List<BankInfo> bankInfoList = bankInfoDao.getBankInfoAll();
        return bankInfoList;
    }
}
