package com.manyi.business.carriersign.support;

import com.manyi.base.entity.MsgCode;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.business.carriersign.CarrierQualificationService;
import com.manyi.business.carriersign.bean.CarrierQualificationBean;
import com.manyi.business.carriersign.bean.CarrierQualificationMsg;
import com.manyi.business.carriersign.exception.BusinessCarrierException;
import com.manyi.business.carriersign.support.dao.CarrierQualificationDao;
import com.manyi.business.carriersign.support.entity.CarrierQualification;
import com.manyi.business.util.upload.FileConst;
import com.manyi.business.util.upload.FileUpload;
import com.manyi.business.util.upload.ReadUploadFileType;
import com.manyi.business.util.urlparse.UrlParse;
import com.manyi.common.fileupload.bean.FileConstBean;
import com.manyi.common.fileupload.bean.FileTypeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhangyufeng on 2015/4/9 0009.
 */
@Service("carrierQualificationService")
public class CarrierQualificationServiceImpl implements CarrierQualificationService {

    private  static final Logger logger = LoggerFactory.getLogger(CarrierQualificationServiceImpl.class);

    @Autowired
    CarrierQualificationDao carrierQualificationDao;

    @Override
    public CarrierQualificationMsg createQualification(CarrierQualificationBean carrierQualificationBean) throws Exception {
        CarrierQualificationMsg msg = new CarrierQualificationMsg();
        int ret = carrierQualificationDao.addQualification(carrierQualificationBean);
        if (ret <= 0){
           // logger.error();
            throw new BusinessException(Type.GROWTH_LEVEL_ERROR);
        }else {
            msg.setCodeMsg(MsgCode.OK);
            return msg;
        }
    }

    @Override
    public List<CarrierQualificationBean> getCarrierQualification(CarrierQualification carrierQualification) {
        List<CarrierQualificationBean> list= carrierQualificationDao.queryQualification(carrierQualification);
        return list;
    }

    @Override
    public int modifyQualification(CarrierQualificationBean carrierQualificationBean) {
        int ret = carrierQualificationDao.updateQualification(carrierQualificationBean);
        return ret;
    }

    @Override
    public int removeQualification(long id) {
        int ret = carrierQualificationDao.deleteQualificationPaper(id);
        return ret;
    }

    @Override
    public void fileSave(String fileUrl,String fileName) throws BusinessCarrierException {
        FileUpload.fileUpload(fileUrl,fileName);
    }

}
