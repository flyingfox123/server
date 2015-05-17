package com.manyi.business.carriersign.exception;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;

/**
 * Created by zhangyufeng on 2015/4/14 0014.
 */
public class BusinessCarrierException extends BusinessException {
    public BusinessCarrierException(Type type) {
        super(type);
    }

    public BusinessCarrierException(String message) {
        super(message);
    }

    public BusinessCarrierException(Type type, String message) {
        super(type, message);
    }

    public BusinessCarrierException(Throwable t, Type type) {
        super(t, type);
    }
}
