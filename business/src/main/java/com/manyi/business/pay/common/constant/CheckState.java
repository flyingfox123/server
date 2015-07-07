package com.manyi.business.pay.common.constant;

import com.manyi.base.entity.*;
import com.manyi.base.entity.Number;

/**
 * @author ZhangYuFeng on 2015/6/25 0025,23:33.
 * @Description:
 * @reviewer:
 */
public enum CheckState {
    MYHAVANOT(Number.NUM_1),
    MYHAVE(Number.NUM_2),
    DIFF(Number.NUM_3);
    private int checkState;
    CheckState(int checkState){
        this.checkState=checkState;
    }
    public int getCheckState(){
        return this.checkState;
    }
}
