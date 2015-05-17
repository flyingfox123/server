package com.manyi.business.freight.support.dao;

import com.manyi.business.freight.bean.*;

/**
 * Created by zhangyufeng on 2015/4/13 0013.
 */
public interface FreightGuaranteeDao {
    /**
     * 增加运费托管主表信息
     * @param freightGuaranteeBean
     * @return
     */
    int addFreightGuarantee(FreightGuaranteeBean freightGuaranteeBean);

    /**
     * 增加合同信息附表
     * @param contractFileBean
     * @return
     */
    int addContractFile(ContractFileBean contractFileBean);

    /**
     * 增加签收信息附表
     * @param signInFileBean
     * @return
     */
    int addSighInFile(SignInFileBean signInFileBean);

    /**
     * 增加支付信息表
     * @param freightChargeBean
     * @return
     */
    int addFreightCharge(FreightChargeBean freightChargeBean);

    /**
     * 增加支付状态变化信息表、支付、延期、退款、确认
     * @param freightStateBean
     * @return
     */
    int addFreightState(FreightStateBean freightStateBean);

    /**
     * 修改运费托管信息表
     * @param freightGuaranteeBean
     * @return
     */
    int modifyFreightGuarantee(FreightGuaranteeBean freightGuaranteeBean);

    /**
     * 修改合同信息附表
     * @param contractFileBean
     * @return
     */
    int modifyContractFile(ContractFileBean contractFileBean);

    /**
     * 修改签收信息附表
     * @param signInFileBean
     * @return
     */
    int modifySighInFile(SignInFileBean signInFileBean);

}
