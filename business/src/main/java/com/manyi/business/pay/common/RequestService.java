package com.manyi.business.pay.common;

import com.manyi.common.util.DoHttpRequest;
import com.manyi.business.pay.log.LogService;
import com.manyi.business.pay.log.bean.BillInterLogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author ZhangYuFeng on 2015/6/9 0009,9:38.
 * @Description: 发送报文之前之后记录报文内容
 * @version:1.0
 * @reviewer:
 */
@Service
public class RequestService {

    @Autowired
    private LogService logService;

    public String doPostRequest(String url,String reqString,Long billId){
        BillInterLogBean billInterLogBean = new BillInterLogBean();
        if (billId!=null){
            billInterLogBean.setBillId(billId);
        }
        billInterLogBean.setReqMessage(reqString);
        logService.addRequestStr(billInterLogBean);
        String respString = DoHttpRequest.doPostRequest(url, reqString);
//        try {
//            respString = new String(respString.getBytes("ISO-8859-1"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        billInterLogBean.setRespMessage(respString);
        logService.addResponseStr(billInterLogBean);

        return respString;
    }
}
