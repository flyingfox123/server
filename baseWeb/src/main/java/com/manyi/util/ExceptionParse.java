package com.manyi.util;

import com.manyi.base.entity.Message;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/16 0016.
 */
public class ExceptionParse {
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ExceptionParse.class);
    static {
        try {
            properties.load(ExceptionParse.class.getClassLoader().getResourceAsStream("exception-code.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Message parseException(HttpServletRequest request) {
        BusinessException exception = (BusinessException)request.getAttribute("ex") ;
        String errorCode = exception.getErrorType().getErrorCode();
        String errorMessage = (String)properties.get(errorCode);
        logger.error("异常代码："+errorCode+",异常内容："+errorMessage);
        Message message = new Message();
        if (errorMessage == null){
            logger.error(errorCode+"未定义");
            message.setCodeMsg(Type.NO_EXCEPTIONMSG.getErrorCode());
            message.setErrorMsg((String)properties.get(Type.NO_EXCEPTIONMSG.getErrorCode()));
            return message;
        }

        message.setCodeMsg(errorCode);
        message.setErrorMsg((String)properties.get(errorCode));
        return message;
    }
}
