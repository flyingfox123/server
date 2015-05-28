package com.manyi.web.common;

import com.manyi.base.entity.Message;
import com.manyi.base.exception.BusinessException;
import com.manyi.common.bean.response.ResponseBean;
import com.manyi.web.util.ExceptionParse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyufeng on 2015/4/16 0016.
 */
@Controller
@RequestMapping(value = "/common")
public class ExceptionHandelController {

    /**
     * 异常统一封装处理controller
     */
    @RequestMapping(value = "/exceptionhandle")
    @ResponseBody
    public ResponseBean exceptionHandle(HttpServletRequest request) {
        ResponseBean responseBean = ExceptionParse.parseException(request);
        return responseBean;
    }
}
