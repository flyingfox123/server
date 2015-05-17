package com.manyi.business.carriersign.exception;

import com.manyi.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver{

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 根据不同错误转向不同页面
        if(ex instanceof BusinessCarrierException) {
            logger.error("系统抛出业务异常");
            return new ModelAndView("forward:/common/exceptionhandle", model);
        } else {
            return new ModelAndView("error", model);
        }
    }

}
