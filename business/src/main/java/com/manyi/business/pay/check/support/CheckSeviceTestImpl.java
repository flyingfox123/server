package com.manyi.business.pay.check.support;

import com.manyi.business.pay.check.support.entity.Check;
import com.manyi.business.pay.check.support.entity.CheckDetail;
import com.manyi.common.util.ReadPropertiesUtil;
import com.manyi.business.pay.check.CheckService;
import com.manyi.business.pay.check.bean.CheckChannelBean;
import com.manyi.business.pay.check.support.dao.DealCheckFileDao;
import com.manyi.business.pay.common.DownLoadFile;
import com.manyi.business.pay.common.util.PayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author ZhangYuFeng
 * @Description:
 * @version: 1.0.0 on 2015/6/11 0011,15:21.
 * @reviewer:
 */
@Service("dealCheckFileSeviceTestImpl")
public class CheckSeviceTestImpl implements CheckService {

    private static final Properties properties = ReadPropertiesUtil.readProperties("config.properties");
    private static final Logger logger = LoggerFactory.getLogger(CheckSeviceTestImpl.class);
    private static final int FILENAMESTART = 74;
    private static final int FILENAMEEND = 105;

    private static final int ARRAYINDEX0=0;
    private static final int ARRAYINDEX1=1;
    private static final int ARRAYINDEX2=2;
    private static final int ARRAYINDEX3=3;
    private static final int ARRAYINDEX4=4;
    private static final int ARRAYINDEX5=5;
    private static final int ARRAYINDEX6=6;
    private static final int ARRAYINDEX7=7;
    private static final int ARRAYINDEX8=8;
    private static final int ARRAYINDEX9=0;
    private static final int ONEHUNDRED=100;

    @Autowired
    private DealCheckFileDao dealCheckFileDao;

    @Override
    public String getCheckFileUrl() {
        return "http://123.129.210.53:8680/hipos/OMCGPUB5/4420781.dow?MERC=800010000050008800010000050008_20150528_01.txtacb05d45a490704d1ff6c422dbea090c";
    }

    @Override
    public Check getCheckFile(String fileUrl) {
        Check check = new Check();
        String fileName = fileUrl.substring(FILENAMESTART,FILENAMEEND);
        String destPath = properties.getProperty("destPath");
        String destFile=destPath+fileName;
        DownLoadFile.doDownLoadFile(fileUrl, destFile);
        check.setCheckFileName(fileName);
        dealCheckFileDao.addCheck(check);
        return check;
    }

    @Override
    public void parseCheckFile(String fileName) {
        String filePath = properties.getProperty("destPath")+fileName;
        FileInputStream fileInputStream = null;
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                fileInputStream = new FileInputStream(file);
                read = new InputStreamReader(fileInputStream,encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i=0;
                List<CheckChannelBean> list = new ArrayList<CheckChannelBean>();
                while((lineTxt = bufferedReader.readLine()) != null){
                    i++;//从第二行开始处理，第一行为汇总信息
                    if (i>1){
                        String[] strings= PayUtil.getStrArr(lineTxt,"\\|");
                        list.add(parseStr2Bean(strings));
                    }
                }
                addCheckFileListToDb(list);
                read.close();
            }else{
                logger.error("找不到指定文件");
            }
        } catch (Exception e) {
            logger.error("读取文件内容出错", e);
        } finally {
            try {
                if (fileInputStream!=null){
                    fileInputStream.close();
                }
                if (read != null){
                    read.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                logger.error("关闭文件错误", e);
            }
        }
    }

    private void addCheckFileListToDb(List<CheckChannelBean> checkChannelBeans){
        dealCheckFileDao.addCheckFileListToDb(checkChannelBeans);
    }

    private CheckChannelBean parseStr2Bean(String[] strings){
        CheckChannelBean checkChannelBean = new CheckChannelBean();
        checkChannelBean.setOrderTime(strings[ARRAYINDEX0]+strings[ARRAYINDEX1]);
        checkChannelBean.setBillNo(strings[ARRAYINDEX2]);
        checkChannelBean.setPayWay(Integer.valueOf(strings[ARRAYINDEX3]));
        checkChannelBean.setBillState(strings[ARRAYINDEX4]);
        double amount = Double.valueOf(strings[ARRAYINDEX5])*ONEHUNDRED;// 转换成分
        checkChannelBean.setAmount(BigDecimal.valueOf(amount));
        double fee = Double.valueOf(strings[ARRAYINDEX6])*ONEHUNDRED;
        checkChannelBean.setFee(BigDecimal.valueOf(fee));
        checkChannelBean.setExchangeNo(strings[ARRAYINDEX7]);
        checkChannelBean.setPayType(Integer.valueOf(strings[ARRAYINDEX8]));
        checkChannelBean.setChannelId(Long.valueOf(strings[ARRAYINDEX9]));
        return checkChannelBean;
    }

    @Override
    public void addCheckFileToDb(String[] strings) {
        CheckChannelBean checkChannelBean = new CheckChannelBean();
        checkChannelBean.setOrderTime(strings[ARRAYINDEX0]+strings[ARRAYINDEX1]);
        checkChannelBean.setBillNo(strings[ARRAYINDEX2]);
        checkChannelBean.setPayWay(Integer.valueOf(strings[ARRAYINDEX3]));
        checkChannelBean.setBillState(strings[ARRAYINDEX4]);
        double amount = Double.valueOf(strings[ARRAYINDEX5])*ONEHUNDRED;// 转换成分
        checkChannelBean.setAmount(BigDecimal.valueOf(amount));
        double fee = Double.valueOf(strings[ARRAYINDEX6])*ONEHUNDRED;
        checkChannelBean.setFee(BigDecimal.valueOf(fee));
        checkChannelBean.setExchangeNo(strings[ARRAYINDEX7]);
        checkChannelBean.setPayType(Integer.valueOf(strings[ARRAYINDEX8]));
        checkChannelBean.setChannelId(Long.valueOf(strings[ARRAYINDEX9]));
        dealCheckFileDao.addCheckFileToDb(checkChannelBean);
    }

    private List<CheckDetail> getBillExist(Long checkId){
        List<CheckDetail> checkDetailList = dealCheckFileDao.getBillExist(checkId);
        return checkDetailList;
    }

    private List<CheckDetail> getCheckExist(Long checkId){
        List<CheckDetail> checkDetailList = dealCheckFileDao.getCheckExist(checkId);
        return checkDetailList;
    }

    private List<CheckDetail> getBillCheckDiff(Long checkId) {
        List<CheckDetail> checkDetailList = dealCheckFileDao.getBillCheckDiff(checkId);
        return checkDetailList;
    }

    private void addCheckDetail(List<CheckDetail> checkDetailList) {
        dealCheckFileDao.addCheckDetail(checkDetailList);
    }
    private void updateBillCheckState(){
        dealCheckFileDao.updateBillCheckState();
    }

    private void updateCheckState(){
        dealCheckFileDao.updateCheckState();
    }

    private void updateDiffState(){
        dealCheckFileDao.updateStateBillDiff();
        dealCheckFileDao.updateStateCheckDiff();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void dealCheckFile() {
        List<CheckDetail> list = new ArrayList<CheckDetail>();
        String fileUrl = getCheckFileUrl(); // 获取对账文件的url
        Check check = getCheckFile(fileUrl);// 下载对账文件，并将对账文件信息存入对账总表
        parseCheckFile(check.getCheckFileName()); // 解析对账文件,将对账文件解析后存入渠道对账表中
        List<CheckDetail> billList = getBillExist(check.getCheckId());
        list.addAll(billList);
        updateBillCheckState();
        List<CheckDetail> checkList = getCheckExist(check.getCheckId());
        list.addAll(checkList);
        updateCheckState();
        List<CheckDetail> diffList = getBillCheckDiff(check.getCheckId());
        list.addAll(diffList);
        updateDiffState();
        addCheckDetail(list);
    }

}
