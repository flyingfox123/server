package com.manyi.business.carriersign.support.dao;

import com.manyi.business.carriersign.bean.CarrierPaperBean;
import com.manyi.business.carriersign.bean.CarrierQualificationBean;
import com.manyi.business.carriersign.support.entity.CarrierQualification;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
public interface CarrierQualificationDao {

    /**
     * 增加承运人资质申请明细主表
     * @param carrierQualificationBean
     * @return
     */
    int addQualification(CarrierQualificationBean carrierQualificationBean);

    /**
     * 增加承运人资质申请附表信息
     * @param carrierPaperBean
     * @return
     */
    int addQualificationDetail(CarrierPaperBean carrierPaperBean);

    /**
     * 查询承运人小规模纳税人申请资质信息主表
     * @param carrierQualification
     * @return
     */
    List<CarrierQualificationBean> queryQualification(CarrierQualification carrierQualification);

    /**
     * 查询承运人小规模纳税人申请资质文件上传表
     * @param id
     * @return
     */
    List<CarrierPaperBean> queryQualificationDetail(String id);

    /**
     * 修改小规模纳税人申请资质信息
     * @param carrierQualificationBean
     * @return
     */
    int updateQualification(CarrierQualificationBean carrierQualificationBean);

    /**
     * 删除小规模纳税人申请资质文件信息
     * @param id
     * @return
     */
    int deleteQualificationPaper(long id);
}
