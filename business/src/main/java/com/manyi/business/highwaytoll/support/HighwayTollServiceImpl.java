package com.manyi.business.highwaytoll.support;

import com.manyi.business.highwaytoll.HighwayTollService;
import com.manyi.business.highwaytoll.bean.HighwayResponseBean;
import com.manyi.business.highwaytoll.bean.HighwayTollBean;
import com.manyi.business.highwaytoll.bean.HistoryRouteBean;
import com.manyi.business.highwaytoll.support.dao.HighwayTollDao;
import com.manyi.business.highwaytoll.util.countHighwayTollUtil;
import com.manyi.business.highwaytoll.util.parseQQmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @Description: 友盟统计
 * @author zhaoyuxin
 * @version: 1.0.0 on 2015/6/16.
 * @reviewer:
 */
@Service
public class HighwayTollServiceImpl implements HighwayTollService {

    @Autowired
    private HighwayTollDao highwayTollDao;
    //千米转换米的距离换算比值
    public static final int CONVERSION_1 =1000;
    //百公里转换公里的距离换算比值
    public static final int CONVERSION_2 =100;
    //收费站之间最近距离5000米
    public static final int CHARGE_POINT_SECTION =2;

    @Override
    public void saveHistoryRoute(HistoryRouteBean historyRouteBean) {
        highwayTollDao.saveHistoryRoute(historyRouteBean);
    }

    @Override
    public List<HistoryRouteBean> getHistoryRoute(long userId) {
        return highwayTollDao.getHistoryRoute(userId);
    }

    @Override
    public void deleteHistoryRoute(long userId) {
        highwayTollDao.deleteHistoryRoute(userId);
        ;
    }

    @Override
    public HighwayResponseBean getTotalFee(HighwayTollBean highwayTollBean) throws IOException {
        DecimalFormat decimalFormat  = new DecimalFormat("##0.00");
        //总距离
         Map map = parseQQmap.getDistanceFromQQmap(highwayTollBean.getBeginX(), highwayTollBean.getBeginY(),
                highwayTollBean.getEndX(), highwayTollBean.getEndY());
        int distance =(Integer)map.get("distance");
        double oilFee=0;
        double totalFee=0;
        double highwayFee=0;
        List sectionList = (List)map.get("sectionList");
        for(int i=0;i<sectionList.size();i++){
            Map sectionMap = (Map)sectionList.get(i);
            double  section = (Double)sectionMap.get("section");
            sectionMap.put("section",decimalFormat.format(section));
            double sectionFee=0;
            if(section> CHARGE_POINT_SECTION) {
                //高速费
                sectionFee = countHighwayTollUtil.getTotalFee(highwayTollBean.getAxisNum(), highwayTollBean.getTotalWeight(), section);
            }
            sectionMap.put("sectionFee",decimalFormat.format(sectionFee));
            //油耗费
            highwayFee=highwayFee+sectionFee;
        }
        oilFee = ((double)distance/ CONVERSION_1) / CONVERSION_2 * (highwayTollBean.getOilWear()) * (highwayTollBean.getUnitPrice());
        totalFee=highwayFee+oilFee;
        HighwayResponseBean highwayResponseBean = new HighwayResponseBean();
        highwayResponseBean.setTotalFee(decimalFormat.format(totalFee));
        highwayResponseBean.setHighwayFee(decimalFormat.format(highwayFee));
        highwayResponseBean.setOilFee(decimalFormat.format(oilFee));
        highwayResponseBean.setChargePoints(sectionList);
        highwayResponseBean.setDistance(decimalFormat.format((long)(distance/ CONVERSION_1)));
        highwayResponseBean.setBridgeNum((Integer)map.get("bridgeNum"));
        highwayResponseBean.setTunnelNum((Integer)map.get("tunnelNum"));
        highwayResponseBean.setProvinceNum((Integer)map.get("provinceNum"));
        highwayResponseBean.setHighwayNum((Integer)map.get("highwayNum"));
        return highwayResponseBean;
    }

}
