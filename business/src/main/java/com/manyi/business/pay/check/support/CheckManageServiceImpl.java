package com.manyi.business.pay.check.support;

import com.manyi.business.pay.check.CheckManageService;
import com.manyi.business.pay.check.bean.CheckDetailBean;
import com.manyi.business.pay.check.support.dao.CheckManageDao;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import com.manyi.business.pay.check.support.entity.CheckSum;
import com.manyi.business.pay.common.constant.CheckState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/25 0025,22:32.
 * @Description:
 * @reviewer:
 */
@Service
public class CheckManageServiceImpl implements CheckManageService {

    @Autowired
    private CheckManageDao checkManageDao;

    private List<CheckDetail> getSumGroupByDay(CheckDetailBean checkDetailBean){
        return checkManageDao.getSumGroupByDay(checkDetailBean);
    }
    @Override
    public List<CheckSum> getCheckSum(CheckDetailBean checkDetailBean) {
        List<CheckDetail> checkDetailList = getSumGroupByDay(checkDetailBean);
        String createDate="";
        List<CheckSum> checkSumList = new ArrayList<CheckSum>();
        CheckSum checkSum = new CheckSum();
        for (CheckDetail checkDetail : checkDetailList){
            if (!createDate.equals(checkDetail.getCreateDate())){
                if (!"".equals(createDate)){
                    checkSumList.add(checkSum);
                }
                createDate=checkDetail.getCreateDate();
                checkSum = new CheckSum();
                checkSum.setCreateDate(createDate);
            }
            if (CheckState.MYHAVANOT.getCheckState()==checkDetail.getState()){
                checkSum.setMyNo(checkDetail.getCount());
            }else if (CheckState.MYHAVE.getCheckState()==checkDetail.getState()){
                checkSum.setMyYes(checkDetail.getCount());
            }else if (CheckState.DIFF.getCheckState()==checkDetail.getState()){
                checkSum.setDiff(checkDetail.getCount());
            }
        }
        if (checkDetailList.size()!=0){
            checkSumList.add(checkSum);
        }
        return checkSumList;
    }

    @Override
    public List<CheckDetail> getCheckDetail(CheckDetailBean checkDetailBean) {
        return checkManageDao.getCheckDetail(checkDetailBean);
    }
}
