package com.manyi.web.business.carriersign;

import com.manyi.base.entity.Message;
import com.manyi.business.carriersign.CarrierQualificationService;
import com.manyi.business.carriersign.bean.CarrierQualificationBean;
import com.manyi.business.carriersign.bean.CarrierQualificationMsg;
import com.manyi.business.carriersign.exception.BusinessCarrierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangyufeng on 2015/4/12 0012.
 */
@Controller
@RequestMapping("/business/carrierqulification")
public class CarrierQulificationController {
    @Autowired
    CarrierQualificationService carrierQualificationService;

    @RequestMapping("/addpic")
    @ResponseBody
    public List getGradeStrategy(HttpServletRequest request) {
        //List list = carrierQualificationService.uploadFileTemp(request);
        return null;
    }

    @RequestMapping("/addQulification")
    @ResponseBody
    public Message addQulification(CarrierQualificationBean carrierQualificationBean) throws Exception{
        CarrierQualificationMsg msg = carrierQualificationService.createQualification(carrierQualificationBean);
        return msg;
    }

    @RequestMapping(value = "/exception")
    @ResponseBody
    public String test(HttpServletRequest request) throws Exception {
        Exception ex = (Exception)request.getAttribute("ex");
        return "success"+ex.getMessage();
    }

    @RequestMapping(value = "/fileSave")
    @ResponseBody
    public void fileSave(HttpServletRequest request) throws BusinessCarrierException{
        String fileUrl="abce2121312\\";
        String fileName="1.jpg";
        carrierQualificationService.fileSave(null,fileName);
    }
}
