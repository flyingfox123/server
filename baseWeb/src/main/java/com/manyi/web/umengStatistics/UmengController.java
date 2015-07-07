package com.manyi.web.umengStatistics;

import com.manyi.business.umengStatistics.UmengStatisticsService;
import com.manyi.business.umengStatistics.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: 友盟统计Controller
 * @author zhaoyuxin
 * @version 1.0.0  2015-06-10.
 * @reviewer:
 */
@Controller
@RequestMapping("/uMeng")
public class UmengController {

    @Autowired
    private UmengStatisticsService umengStatisticsService;

    private static final int pageSize=20;

    private static final int page=1;

    /**
     * 获取apps列表
     * @return
     */
    @RequestMapping("/appsData/select")
    @ResponseBody
    public UmengAppsData[] GetAppsData() throws Exception {
        UmengAppsData[] umengAppsDatas = umengStatisticsService.GetAppsData(pageSize,page,"");
        return umengAppsDatas;
    }

    /**
     * 所有app的基本数据
     * @return
     */
    @RequestMapping("/baseData/select")
    @ResponseBody
    public UmengUserBaseData getAppsBaseData() throws Exception {
        UmengUserBaseData umengUserBaseData = umengStatisticsService.GetUserBaseData();
        return umengUserBaseData;
    }


    /**
     * App版本数据
     * @return
     */
    @RequestMapping("/appVersions/select")
    @ResponseBody
    public UmengAppVersionsData[] getAppVersions( String appKey,  String date) throws Exception {
        UmengAppVersionsData[] umengAppVersionsDatas = umengStatisticsService.GetUmengAppVersionsData(appKey,date);
        return umengAppVersionsDatas;
    }


    /**
     * App渠道数据
     * @return
     */
    @RequestMapping("/appChannels/select")
    @ResponseBody
    public UmengAppChannelsData[] getChannelsData( String appKey,  String date,String size,String page) throws Exception {
        UmengAppChannelsData[] umengAppChannelsDatas = umengStatisticsService.GetUmengAppChannelsData(appKey, date,size,page);
        return umengAppChannelsDatas;
    }

    /**
     * app今日数据
     * @return
     */
    @RequestMapping("/todayData/select")
    @ResponseBody
    public UmengUserdata GetTodayUserData(String appKey) throws Exception {
        UmengUserdata umengUserdata = umengStatisticsService.GetTodayUserData(appKey);
        return umengUserdata;
    }


    /**
     * app昨日数据
     * @return
     */
    @RequestMapping("/yesterdayData/select")
    @ResponseBody
    public UmengUserdata GetYesterdayUserData(String appKey) throws Exception {
        UmengUserdata umengUserdata = umengStatisticsService.GetYesterdayUserData(appKey);
        return umengUserdata;
    }

    /**
     * 获取制定app任意日期数据
     * @return
     */
    @RequestMapping("/AnyDateData/select")
    @ResponseBody
    public UmengUserdata GetUserDataByDate(String appKey,String date) throws Exception {
        UmengUserdata umengUserdata = umengStatisticsService.GetUserDataByDate(appKey,date);
        return umengUserdata;
    }

    /**
     * 获取新增用户汇总
     * @return
     */
    @RequestMapping("/newUsers/select")
    @ResponseBody
    public List<Map> GetNewUsers( String appKey,String start_date,String end_date,String period_type, String channels,
                                     String versions) throws Exception {
        List<Map> newUsers = umengStatisticsService.GetNewUsers(appKey, start_date,end_date,period_type,channels,versions);
        return newUsers;
    }

    /**
     * 获取活跃用户汇总
     * @return
     */
    @RequestMapping("/activeUsers/select")
    @ResponseBody
    public List<Map> GetActiveUsers( String appKey,String start_date,String end_date,String period_type, String channels,
                                 String versions) throws Exception {
        List<Map> newUsers = umengStatisticsService.GetActiveUsers(appKey, start_date, end_date, period_type, channels, versions);
        return newUsers;
    }


    /**
     * 获取启动次数汇总
     * @return
     */
    @RequestMapping("/launches/select")
    @ResponseBody
    public List<Map> GetLaunchesData( String appKey,String start_date,String end_date,String period_type, String channels,
                                    String versions) throws Exception {
        List<Map> newUsers = umengStatisticsService.GetLaunchesData(appKey, start_date, end_date, period_type, channels, versions);
        return newUsers;
    }

    /**
     * 获取使用时长汇总
     * @return
     */
    @RequestMapping("/durations/select")
    @ResponseBody
    public void GetDurationsData( String appKey,String start_date,String end_date,String period_type, String channels,
                                     String versions) throws Exception {
        umengStatisticsService.GetDurationsData(appKey, start_date, end_date, period_type, channels, versions);
     //   return newUsers;
    }
}
