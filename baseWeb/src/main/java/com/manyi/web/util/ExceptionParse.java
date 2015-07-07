package com.manyi.web.util;

import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.bean.response.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author ZhangYuFeng on 2015/6/15 0015,15:11.
 * @Description:
 * @version: 1.0.0
 * @reviewer:
 */
public class ExceptionParse {
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ExceptionParse.class);
    static {
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("exception-code.properties"));
            properties.load(inputStreamReader);
        } catch (IOException e) {
            logger.error("",e);
        }finally {
            if (inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }
    public static ResponseBean parseException(HttpServletRequest request) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setState("fail");
        BusinessException exception = (BusinessException)request.getAttribute("ex") ;
        String errorCode = exception.getErrorType().getErrorCode();
        String errorMessage = (String)properties.get(errorCode);
        logger.error("异常代码："+errorCode+",异常内容："+errorMessage);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        logger.error(sw.toString());
        logger.error(exception.getMessage());
        // Message message = new Message();
        if (errorMessage == null){
            logger.error(errorCode+"未定义");
            //message.setCodeMsg(Type.NO_EXCEPTIONMSG.getErrorCode());
            responseBean.setErrCode(Type.NO_EXCEPTIONMSG.getErrorCode());
            responseBean.setErrMsg((String) properties.get(Type.NO_EXCEPTIONMSG.getErrorCode()));
            return responseBean;
        }
        responseBean.setErrCode(errorCode);
        responseBean.setErrMsg(errorMessage);
        //message.setCodeMsg(errorCode);
        return responseBean;
    }
}