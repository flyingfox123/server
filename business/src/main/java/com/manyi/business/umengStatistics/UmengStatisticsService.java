package com.manyi.business.umengStatistics;

import com.manyi.business.umengStatistics.bean.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 友盟Service
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-24
 * @reviewer:
 */
public interface UmengStatisticsService {

    /**
     * 获取Umeng中Atoken
     * @return
     * @throws Exception
     */
    public String TokenAuthorization() throws Exception;

    /**
     *  获取apps列表
     * @param per_page 每⻚页数量，默认为20 非必须
     * @param page  第几页，默认为1,从1计数 非必须
     * @param q 要查询的app名  非必须
     * @return
     * @throws Exception
     */
    public UmengAppsData[] GetAppsData (int per_page,int page,String q)throws Exception;


    /**
     * 获取所有app的基本数据
     * @return
     * @throws Exception
     */
    public UmengUserBaseData GetUserBaseData ()throws Exception;

    /**
     * 获取App渠道数据
     * @param appKey app的标识
     * @param date 查询日期, 格式为2013-03-01,也可以是today或 yesterday,today为默认值。非必须
     * @param pageNum 单页显示数量, 默认为10 非必须
     * @param page 当前页数,默认为1 非必须
     * @return
     * @throws Exception
     */
    public UmengAppChannelsData[] GetUmengAppChannelsData(String appKey,String date,String pageNum,String page)throws Exception;


    /**
     * 获取App版本数据
     * @param appKey app的标识
     * @param date 查询日期, 格式为2013-03-01,也可以是today或 yesterday,today为默认值。非必须
     * @return
     * @throws Exception
     */
    public UmengAppVersionsData[] GetUmengAppVersionsData(String appKey,String date)throws Exception;

    /**
     * 获取指定app今日数据
     * @param appKey app的标识
     * @return
     * @throws Exception
     */
    public UmengUserdata GetTodayUserData(String appKey) throws Exception;


    /**
     * 获取指定app昨日数据
     * @param appKey app的标识
     * @return
     * @throws Exception
     */
    public UmengUserdata GetYesterdayUserData(String appKey) throws Exception;

    /**
     * 获取制定app任意日期数据
     * @param appKey app的标识
     * @param date 格式2015-01-01
     * @return
     * @throws Exception
     */
    public UmengUserdata GetUserDataByDate(String appKey,String date) throws Exception;

    /**
     * 获取新增用户汇总
     * @param appKey appKey app的标识
     * @param start_date 开始⽇日期 2012-08-15
     * @param end_date 结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels 渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions 版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    public List<Map> GetNewUsers(String appKey,String start_date,String end_date,String period_type, String channels,
                                 String versions) throws Exception;


    /**
     * 获取活跃用户汇总
     * @param appKey appKey app的标识
     * @param start_date 开始⽇日期 2012-08-15
     * @param end_date 结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels 渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions 版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    public List<Map> GetActiveUsers(String appKey,String start_date,String end_date,String period_type, String channels,
                                    String versions) throws Exception;

    /**
     * 获取启动次数汇总
     * @param appKey appKey app的标识
     * @param start_date 开始⽇日期 2012-08-15
     * @param end_date 结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels 渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions 版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    public List<Map> GetLaunchesData(String appKey,String start_date,String end_date,String period_type, String channels,
                                     String versions) throws Exception;


    /**
     * 获取使用时长汇总
     * @param appKey appKey app的标识
     * @param start_date 开始⽇日期 2012-08-15
     * @param end_date 结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels 渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions 版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    public void GetDurationsData(String appKey,String start_date,String end_date,String period_type, String channels,
                                 String versions) throws Exception;
}
