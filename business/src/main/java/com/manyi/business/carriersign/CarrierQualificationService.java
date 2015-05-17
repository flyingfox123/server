package com.manyi.business.carriersign;

import com.manyi.business.carriersign.bean.CarrierQualificationBean;
import com.manyi.business.carriersign.bean.CarrierQualificationMsg;
import com.manyi.business.carriersign.exception.BusinessCarrierException;
import com.manyi.business.carriersign.support.entity.CarrierQualification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
public interface CarrierQualificationService {

    /**
     * 创建一条资质申请信息
     * @param carrierQualificationBean
     * @return
     */
    CarrierQualificationMsg createQualification(CarrierQualificationBean carrierQualificationBean) throws Exception;


    /**
     * 查询承运人资质申请信息
     * @param carrierQualification
     * @return
     */
    List<CarrierQualificationBean> getCarrierQualification(CarrierQualification carrierQualification);


    /**
     * 修改承运人申请资质信息
     * @param carrierQualificationBean
     * @return
     */
    int modifyQualification(CarrierQualificationBean carrierQualificationBean);

    /**
     * 删除承运人申请资质信息
     * @param id
     * @return
     */
    int removeQualification(long id);

    /**
     * 将文件从临时目录中存入正式目录
     * @param fileUrl
     * @param fileName
     */
    public void fileSave(String fileUrl,String fileName) throws BusinessCarrierException;



}
