package com.manyi.business.pay.common.constant;

/**
 * @author ZhangYuFeng on 2015/6/16 0016,16:14.
 * @Description:
 * @reviewer:
 */
public enum ChannelType {
    ETC(1);

    private int channelType;
    ChannelType(int channelType){
        this.channelType=channelType;
    }

    public int getChannelType(){
        return this.channelType;
    }
}
