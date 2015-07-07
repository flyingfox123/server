package com.manyi.business.pay.common.constant;

import com.manyi.base.entity.*;
import com.manyi.base.entity.Number;

/**
 * @author ZhangYuFeng on 2015/6/16 0016,16:02.
 * @Description:
 * @reviewer:
 */
public enum BillEnum {
    MONEYGUR(Number.NUM_1),// 运费担保
    QC(Number.NUM_2),// 圈存
    FK(Number.NUM_3); // 代缴罚款

    private int billEnum;

    BillEnum(int billEnum) {
        this.billEnum = billEnum;
    }

    public int getBillEnum() {
        return this.billEnum;
    }
}
