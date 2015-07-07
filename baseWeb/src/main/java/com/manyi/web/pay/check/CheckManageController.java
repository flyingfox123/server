package com.manyi.web.pay.check;

import com.manyi.business.pay.check.CheckManageService;
import com.manyi.business.pay.check.bean.CheckDetailBean;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import com.manyi.business.pay.check.support.entity.CheckSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/26 0026,10:04.
 * @Description:
 * @reviewer:
 */
@Controller
@RequestMapping("pay/check")
public class CheckManageController {

    @Autowired
    private CheckManageService checkManageService;

    @RequestMapping("/manage/getCheckSum")
    @ResponseBody
    public List<CheckSum> getCheckSum(CheckDetailBean checkDetailBean){
        List<CheckSum> checkSumList = checkManageService.getCheckSum(checkDetailBean);
        return checkSumList;
    }

    @RequestMapping("/manage/getCheckDetail")
    @ResponseBody
    public List<CheckDetail> getCheckDetail(CheckDetailBean checkDetailBean) {
        List<CheckDetail> checkDetailList = checkManageService.getCheckDetail(checkDetailBean);
        return checkDetailList;
    }
}
