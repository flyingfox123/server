package com.manyi.business.carriersign.exception;

import com.manyi.base.exception.BusinessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
public class ExceptionHandler  implements HandlerExceptionResolver{

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 根据不同错误转向不同页面
        if(ex instanceof BusinessException) {
            return new ModelAndView("forward:test.do", model);
        } else {
            return new ModelAndView("error", model);
        }
    }
}
