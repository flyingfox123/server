package com.manyi.usercenter.exception;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;

/**
 * Created by zhangyufeng on 2015/5/11 0011.
 */
public class UserLoginException extends BusinessException {

    public UserLoginException(Type type, String message) {
        super(type, message);
    }
    public UserLoginException(Type type) {
        super(type);
    }
}
