package com.manyi.usercenter.exception;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;

/**
 * Created by zhangyufeng on 2015/5/12 0012.
 */
public class UserGetMsgCodeException extends BusinessException {
    public UserGetMsgCodeException(Type type, String message) {
        super(type, message);
    }
    public UserGetMsgCodeException(Type type) {
        super(type);
    }
}
