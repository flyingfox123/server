package com.manyi.business.umengStatistics.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manyi.business.umengStatistics.UmengStatisticsService;
import com.manyi.business.umengStatistics.bean.*;
import com.manyi.business.umengStatistics.util.UmengUtil;
import com.manyi.common.util.DoHttpRequest;
import com.manyi.common.util.ReadPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Description: 友盟统计
 * @author zhaoyuxin
 * @version 1.0.0 2015-06-18
 * @reviewer:
 */
@Service
public class UmengStatisticsServiceImpl implements UmengStatisticsService {

    private  final String email = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("email");

    private  final String password = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("password");

    private  final String umengAuthUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengAuthUrl");

    private  final String umengUserdataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengUserdataUrl");

    private  final String umengAppChannelsUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengAppChannelsUrl");

    private  final String umengAppVersionsUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengAppVersionsUrl");

    private  final String umengTodayUserdataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengTodayUserdataUrl");

    private  final String umengYesterdayUserdataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengYesterdayUserdataUrl");

    private  final String umengUserBasedataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengUserBasedataUrl");

    private  final String umengGetNewUsersUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengGetNewUsersUrl");

    private  final String umengGetActiveUsersUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengGetActiveUsersUrl");

    private  final String umengGetLaunchesDataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengGetLaunchesDataUrl");

    private  final String umengGetDurationsDataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengGetDurationsDataUrl");

    private  final String umengGetAppsDataUrl = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("umengGetAppsDataUrl");

    private  final String appKey = ReadPropertiesUtil.readProperties("umeng.properties").getProperty("appKey");

    private  static final int normalCode=200;

    private  static final int errorCode=401;

    private  final String error="error";
    /**
     * 获取Umeng中Atoken
     *
     * @return
     * @throws Exception
     */
    @Override
    public String TokenAuthorization() throws Exception {
        String result = null;
        String token = null;
        int code;
        ObjectMapper objectMapper = new ObjectMapper();
        //拼接参数，并将密码中特殊字符转移
        String param = "email=" + email + "&password=" + URLEncoder.encode(password, "utf-8");
        result = DoHttpRequest.doPostRequest(umengAuthUrl, param);
        //json转换成map
        Map maps = objectMapper.readValue(result, Map.class);
        code = (Integer) maps.get("code");
        if (errorCode == code) {
            token = error;
        }
        if (normalCode == code) {
            token = (String) maps.get("auth_token");
        }
        return token;
    }


    /**
     * 获取apps列表
     *
     * @param per_page 每⻚页数量，默认为20 非必须
     * @param page     第几页，默认为1,从1计数 非必须
     * @param AppName        要查询的app名  非必须
     * @return
     * @throws Exception
     */
    @Override
    public UmengAppsData[] GetAppsData(int per_page, int page, String AppName) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengAppsData[] UmengAppsData = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("&auth_token=").append(token);
            if (!StringUtils.isEmpty(per_page)) {
                param.append("&per_page=").append(per_page);
            }
            if (!StringUtils.isEmpty(page)) {
                param.append("&page=").append(page);
            }
            result = DoHttpRequest.doGetRequest(umengGetAppsDataUrl, param.toString());
            UmengAppsData = objectMapper.readValue(result, UmengAppsData[].class);
        }
        return UmengAppsData;
    }

    /**
     * 获取所有app的基本数据
     *
     * @return
     * @throws Exception
     */
    @Override
    public UmengUserBaseData GetUserBaseData() throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengUserBaseData umengUserBaseData = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String param =  "auth_token=" + token;
            result = DoHttpRequest.doGetRequest(umengUserBasedataUrl, param);
            umengUserBaseData = objectMapper.readValue(result, UmengUserBaseData.class);

        }
        return umengUserBaseData;
    }


    /**
     * 获取App渠道数据
     *
     * @param appKey  app的标识
     * @param date    查询日期, 格式为2013-03-01,也可以是today或 yesterday,today为默认值。非必须
     * @param pageNum 单页显示数量, 默认为10 非必须
     * @param page    当前页数,默认为1 非必须
     * @return
     * @throws Exception
     */
    @Override
    public UmengAppChannelsData[] GetUmengAppChannelsData(String appKey, String date, String pageNum, String page) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengAppChannelsData[] umengAppChannelsDatas = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token);
            if (!StringUtils.isEmpty(date)) {
                param.append("&date=").append(date);
            }
            if (!StringUtils.isEmpty(pageNum)) {
                param.append("&per_page=").append(pageNum);
            }
            if (!StringUtils.isEmpty(page)) {
                param.append("&page=").append(page);
            }
            result = DoHttpRequest.doGetRequest(umengAppChannelsUrl, param.toString());
            umengAppChannelsDatas = objectMapper.readValue(result, UmengAppChannelsData[].class);
        }
        return umengAppChannelsDatas;
    }

    /**
     * 获取App版本数据
     *
     * @param appKey app的标识
     * @param date   查询日期, 格式为2013-03-01,也可以是today或 yesterday,today为默认值。非必须
     * @return
     * @throws Exception
     */
    @Override
    public UmengAppVersionsData[] GetUmengAppVersionsData(String appKey, String date) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengAppVersionsData[] umengAppVersionsData = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token);
            if (!StringUtils.isEmpty(date)) {
                param.append("&date=").append(date);
            }
            result = DoHttpRequest.doGetRequest(umengAppVersionsUrl, param.toString());
            umengAppVersionsData = objectMapper.readValue(result, UmengAppVersionsData[].class);

        }
        return umengAppVersionsData;
    }

    /**
     * 获取指定app今日数据
     *
     * @param appKey app的标识
     * @return
     * @throws Exception
     */
    @Override
    public UmengUserdata GetTodayUserData(String appKey) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengUserdata userdatabean = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String param = "appkey=" + appKey + "&auth_token=" + token;
            result = DoHttpRequest.doGetRequest(umengTodayUserdataUrl, param);
            userdatabean = objectMapper.readValue(result, UmengUserdata.class);

        }
        return userdatabean;
    }

    /**
     * 获取指定app昨日数据
     *
     * @param appKey app的标识
     * @return
     * @throws Exception
     */
    @Override
    public UmengUserdata GetYesterdayUserData(String appKey) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengUserdata userdatabean = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String param = "appkey=" + appKey + "&auth_token=" + token;
            result = DoHttpRequest.doGetRequest(umengYesterdayUserdataUrl, param);
            userdatabean = objectMapper.readValue(result, UmengUserdata.class);

        }
        return userdatabean;
    }

    /**
     * 获取制定app任意日期数据
     *
     * @param appKey app的标识
     * @param date   格式2015-01-01
     * @return
     * @throws Exception
     */
    @Override
    public UmengUserdata GetUserDataByDate(String appKey, String date) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        UmengUserdata userdatabean = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String param = "appkey=" + appKey + "&date=" + date + "&auth_token=" + token;
            result = DoHttpRequest.doGetRequest(umengUserdataUrl, param);
            userdatabean = objectMapper.readValue(result, UmengUserdata.class);
        }

        return userdatabean;
    }

    /**
     * 获取新增用户汇总
     *
     * @param appKey      appKey app的标识
     * @param start_date  开始⽇日期 2012-08-15
     * @param end_date    结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels    渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions    版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    @Override
    public List<Map> GetNewUsers(String appKey, String start_date, String end_date, String period_type, String channels,
                                 String versions) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        List<Map> resultList = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token)
                    .append("&start_date=").append(start_date).append("&end_date=").append(end_date);
            if (!StringUtils.isEmpty(period_type)) {
                param.append("&period_type=").append(period_type);
            }
            if (!StringUtils.isEmpty(channels)) {
                param.append("&channels=").append(channels);
            }
            if (!StringUtils.isEmpty(versions)) {
                param.append("&versions=").append(versions);
            }
            result = DoHttpRequest.doGetRequest(umengGetNewUsersUrl, param.toString());
            Map maps = objectMapper.readValue(result, Map.class);
            resultList = UmengUtil.DealWithUmengResult(maps);

        }
        return resultList;
    }

    /**
     * 获取活跃用户汇总
     *
     * @param appKey      appKey app的标识
     * @param start_date  开始⽇日期 2012-08-15
     * @param end_date    结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels    渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions    版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    @Override
    public List<Map> GetActiveUsers(String appKey, String start_date, String end_date, String period_type, String channels,
                                    String versions) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        List<Map> resultList = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token)
                    .append("&start_date=").append(start_date).append("&end_date=").append(end_date);
            if (!StringUtils.isEmpty(period_type)) {
                param.append("&period_type=").append(period_type);
            }
            if (!StringUtils.isEmpty(channels)) {
                param.append("&channels=").append(channels);
            }
            if (!StringUtils.isEmpty(versions)) {
                param.append("&versions=").append(versions);
            }
            result = DoHttpRequest.doGetRequest(umengGetActiveUsersUrl, param.toString());
            result = result.replace("/n", "");
            Map maps = objectMapper.readValue(result, Map.class);
            resultList = UmengUtil.DealWithUmengResult(maps);

        }
        return resultList;
    }

    /**
     * 获取启动次数汇总
     *
     * @param appKey      appKey app的标识
     * @param start_date  开始⽇日期 2012-08-15
     * @param end_date    结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels    渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions    版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    @Override
    public List<Map> GetLaunchesData(String appKey, String start_date, String end_date, String period_type, String channels,
                                     String versions) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        List<Map> resultList = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token)
                    .append("&start_date=").append(start_date).append("&end_date=").append(end_date);
            if (!StringUtils.isEmpty(period_type)) {
                param.append("&period_type=").append(period_type);
            }
            if (!StringUtils.isEmpty(channels)) {
                param.append("&channels=").append(channels);
            }
            if (!StringUtils.isEmpty(versions)) {
                param.append("&versions=").append(versions);
            }
            result = DoHttpRequest.doGetRequest(umengGetLaunchesDataUrl, param.toString());
            Map maps = objectMapper.readValue(result, Map.class);
            resultList = UmengUtil.DealWithUmengResult(maps);

        }
        return resultList;
    }

    /**
     * 获取使用时长汇总
     *
     * @param appKey      appKey app的标识
     * @param start_date  开始⽇日期 2012-08-15
     * @param end_date    结束⽇日期 2012-09-04
     * @param period_type 日期类型 daily , weekly , monthly , 默认为 daily  非必须
     * @param channels    渠道id,以,分割4f6c5c4852701534c9000007,4f86490752701575f5000004  非必须
     * @param versions    版本号以,分割 1.1.0,1.1.3  非必须
     * @throws Exception
     */
    @Override
    public void GetDurationsData(String appKey, String start_date, String end_date, String period_type, String channels,
                                 String versions) throws Exception {
        String token = TokenAuthorization();
        String result = null;
        if (!error.equals(token)) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder param = new StringBuilder("appkey=").append(appKey).append("&auth_token=").append(token)
                    .append("&start_date=").append(start_date).append("&end_date=").append(end_date);
            if (!StringUtils.isEmpty(period_type)) {
                param.append("&period_type=").append(period_type);
            }
            if (!StringUtils.isEmpty(channels)) {
                param.append("&channels=").append(channels);
            }
            if (!StringUtils.isEmpty(versions)) {
                param.append("&versions=").append(versions);
            }
            result = DoHttpRequest.doGetRequest(umengGetDurationsDataUrl, param.toString());
            objectMapper.readValue(result, Map.class);

        }
    }
}
