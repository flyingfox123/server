package com.manyi.business.pay.check.support.dao;

import com.manyi.business.pay.check.bean.CheckChannelBean;
import com.manyi.business.pay.check.support.entity.Check;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/11 0011,18:24.
 * @Description:
 * @reviewer:
 */
public interface DealCheckFileDao {

    /**
     * 将对账文件存入到对账总表
     * @param check
     */
    void addCheck(Check check);

    /**
     * 将对账文件解析之后存入渠道对账表中
     * @param checkChannelBean
     */
    void addCheckFileToDb(CheckChannelBean checkChannelBean);

    /**
     * 对账数据批量插入数据库
     * @param checkChannelBeans
     */
    void addCheckFileListToDb(List<CheckChannelBean> checkChannelBeans);

    /**
     * 获取账单表中存在而对账明细表中不存在的数据
     * @return
     */
    List<CheckDetail> getBillExist(@Param("checkId")Long checkId);


    /**
     * 获取对账表中存在而账单表中不存在的数据
     * @return
     */
    List<CheckDetail> getCheckExist(Long checkId);

    /**
     * 获取账单表与对账表金额不一致的数据
     * @return
     */
    List<CheckDetail> getBillCheckDiff(Long checkId);

    /**
     * 将对账结果存入对账结果表中
     * @param checkDetailList
     */
    void addCheckDetail(List<CheckDetail> checkDetailList);

    /**
     * 查出账单表中存在，对账表中不存在的数据后，
     * 存入对账结果表，然后更新账单表中的对账状态
     */
    void updateBillCheckState();

    /**
     * 查出对账表中存在的数据，而账单表中不存在的数据，
     * 存入对账结果表，然后更新对账表中的对账状态
     */
    void updateCheckState();

    /**
     * 更新账单表中，与对账表金额不一致的对账状态
     */
    void updateStateBillDiff();

    /**
     * 更新对账表中，与账单表金额不一致的对账状态
     */
    void updateStateCheckDiff();
}
