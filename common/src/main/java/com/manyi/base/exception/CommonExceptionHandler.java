package com.manyi.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommonExceptionHandler implements HandlerExceptionResolver {

    //private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @Override
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 统一异常处理页
        return new ModelAndView("forward:/common/exceptionhandle", model);

    }

    public static void main(String args[]){
        CommonExceptionHandler commonExceptionHandler = new CommonExceptionHandler();
    }

}