package com.manyi.business.umengStatistics.support;

import com.manyi.business.umengStatistics.bean.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-business-servlet-test.xml"})
public class UmengStatisticsServiceImplTest {

    @Autowired
    public UmengStatisticsServiceImpl umengStatisticsServiceImpl;

    @Test
    public void testTokenAuthorization() throws Exception {
        String result = umengStatisticsServiceImpl.TokenAuthorization();
        assertNotNull(result);
        assertNotEquals(result,"error");
    }

    @Test
    public void testUmengUserBaseData() throws Exception {
        UmengUserBaseData umengUserBaseData = umengStatisticsServiceImpl.GetUserBaseData();
        assertNotNull(umengUserBaseData);
    }

    @Test
    public void testGetAppsData() throws Exception {
        UmengAppsData[]  umengAppsData = umengStatisticsServiceImpl.GetAppsData(20,1,"");
        assertNotNull(umengAppsData);
    }

    @Test
    public void testGetUmengAppChannelsData() throws Exception {
        UmengAppChannelsData[] umengAppChannelsDataArray= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "","","");
        UmengAppChannelsData[] umengAppChannelsDataArray4= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "2015-05-19","","");
        UmengAppChannelsData[] umengAppChannelsDataArray1= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "","20","");
        UmengAppChannelsData[] umengAppChannelsDataArray2= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "","","4");
        UmengAppChannelsData[] umengAppChannelsDataArray3= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "2015-05-19","","3");
        UmengAppChannelsData[] umengAppChannelsDataArray5= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "2015-05-19","12","");
        UmengAppChannelsData[] umengAppChannelsDataArray6= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "","15","3");
        UmengAppChannelsData[] umengAppChannelsDataArray7= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "2015-05-19","14","3");
        UmengAppChannelsData[] umengAppChannelsDataArray8= umengStatisticsServiceImpl.GetUmengAppChannelsData("55558ad7e0f55a9f70003cfc", "yesterday","14","3");
        assertNotNull(umengAppChannelsDataArray);
        assertNotNull(umengAppChannelsDataArray1);
        assertNotNull(umengAppChannelsDataArray2);
        assertNotNull(umengAppChannelsDataArray3);
        assertNotNull(umengAppChannelsDataArray4);
        assertNotNull(umengAppChannelsDataArray5);
        assertNotNull(umengAppChannelsDataArray6);
        assertNotNull(umengAppChannelsDataArray7);
        assertNotNull(umengAppChannelsDataArray8);
    }

    @Test
    public void testGetUmengAppVersionsData() throws Exception {
        UmengAppVersionsData[] umengAppVersionsDataArray= umengStatisticsServiceImpl.GetUmengAppVersionsData("55558ad7e0f55a9f70003cfc", "2015-05-19");
        UmengAppVersionsData[] umengAppVersionsDataArray1= umengStatisticsServiceImpl.GetUmengAppVersionsData("55558ad7e0f55a9f70003cfc", "");
        UmengAppVersionsData[] umengAppVersionsDataArray2= umengStatisticsServiceImpl.GetUmengAppVersionsData("55558ad7e0f55a9f70003cfc", "today");
        assertNotNull(umengAppVersionsDataArray);
        assertNotNull(umengAppVersionsDataArray1);
        assertNotNull(umengAppVersionsDataArray2);
    }

    @Test
    public void testGetTodayUserData() throws Exception {
        UmengUserdata userdatabean = umengStatisticsServiceImpl.GetTodayUserData("55558ad7e0f55a9f70003cfc");
        assertNotNull(userdatabean);
    }

    @Test
    public void testGetYesterdayUserData() throws Exception {
        UmengUserdata userdatabean = umengStatisticsServiceImpl.GetYesterdayUserData("55558ad7e0f55a9f70003cfc");
        assertNotNull(userdatabean);
    }

    @Test
    public void testGetUserDataByDate() throws Exception {
        UmengUserdata userdatabean=umengStatisticsServiceImpl.GetUserDataByDate("55558ad7e0f55a9f70003cfc", "2015-05-19");
        assertNotNull(userdatabean);
    }

    @Test
    public void testGetNewUsers() throws Exception {
        List<Map> result1 = umengStatisticsServiceImpl.GetNewUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","55594830e0f55a15340050b7","1.0");
        List<Map> result2 = umengStatisticsServiceImpl.GetNewUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","","1.0");
        List<Map> result3 = umengStatisticsServiceImpl.GetNewUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","55594830e0f55a15340050b7","");
        List<Map> result4 = umengStatisticsServiceImpl.GetNewUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","","");
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertNotNull(result4);
    }

    @Test
    public void testGetActiveUsers() throws Exception {
        List<Map> result1 = umengStatisticsServiceImpl.GetActiveUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","55594830e0f55a15340050b7","1.0");
        List<Map> result2 = umengStatisticsServiceImpl.GetActiveUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","","1.0");
        List<Map> result3 =  umengStatisticsServiceImpl.GetActiveUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","55594830e0f55a15340050b7","");
        List<Map> result4 =  umengStatisticsServiceImpl.GetActiveUsers("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","","","");
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertNotNull(result4);
    }

    @Test
    public void testGetLaunchesData() throws Exception {
        List<Map> result1 =umengStatisticsServiceImpl.GetLaunchesData("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","weekly","55594830e0f55a15340050b7","1.0");
        List<Map> result2 =umengStatisticsServiceImpl.GetLaunchesData("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","weekly","55594830e0f55a15340050b7","");
        List<Map> result3 =umengStatisticsServiceImpl.GetLaunchesData("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","weekly","","1.0");
        List<Map> result4 = umengStatisticsServiceImpl.GetLaunchesData("55558ad7e0f55a9f70003cfc", "2015-05-01", "2015-05-19","weekly","","");
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertNotNull(result4);
    }

    @Test
    public void testGetDurationsData() throws Exception {
        umengStatisticsServiceImpl.GetDurationsData("55558ad7e0f55a9f70003cfc", "2015-05-19", "2015-05-19","","55594830e0f55a15340050b7","1.0");
    }
}