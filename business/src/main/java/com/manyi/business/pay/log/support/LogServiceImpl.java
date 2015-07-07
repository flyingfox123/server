package com.manyi.business.pay.log.support;

import com.manyi.business.pay.log.LogService;
import com.manyi.business.pay.log.bean.BillInterLogBean;
import com.manyi.business.pay.log.support.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangyufeng on 2015/6/9 0009.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addRequestStr(BillInterLogBean billInterLogBean) {
        logDao.addRequestStr(billInterLogBean);
    }

    @Override
    public void addResponseStr(BillInterLogBean billInterLogBean) {
        logDao.addResponseStr(billInterLogBean);
    }
}
