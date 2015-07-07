package com.manyi.business.highwaytoll.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manyi.common.util.DoHttpRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Description: 解析qq地图
 * @author zhaoyuxin
 * @version: 1.0.0 2015-06-18
 * @reviewer:
 */
public class parseQQmap {

    //腾讯地图地址
    private static  String QQ_MAP_URL = "http://routes.map.qq.com";
    //腾讯地图获取收费站省份地址
    private static  String GET_PROVINCE_URL = "http://apis.map.qq.com/ws/geocoder/v1";
    //腾讯地图api秘钥
    private static  String APIKEY = "7RHBZ-YO5RP-NELDJ-VRAP2-5JJUQ-SKBHC";
    //腾讯地图地址
    private final static  int DIVISOR = 100;
    //π
    private static  double M_PI = Math.PI;
    //地球半径
    private final static  double EARTH_R = 6378137;
    //地球周长
    private final static  double EARTH_P = 20037508.34;
    //收费站类型
    private final static  int TIPTIPE_CHARGE = 5;
    //隧道类型
    private final static  int TIPTIPE_TUNNEL = 7;
    //请求成功的平均数
    private final static  int REQ_SUCCESS_AVER = 10;
    //数字2
    private final static  int NUM_2 = 2;
    //数字3
    private final static  int NUM_3 = 3;
    //数字1000
    private final static  int NUM_1000 = 1000;
    //弧度180
    private final static  double radian_180 = 180.0;
    //数字2.0
    private final static  double DOUBLE_2 = 2.0;
    /**
     * 请求qq地图返回结果
     *
     * @param beginX
     * @param beginY
     * @param EndX
     * @param EndY
     * @return
     * @throws IOException
     */
    public static Map getDistanceFromQQmap(String beginX, String beginY, String EndX, String EndY) throws IOException {
        int bridgeNum = 0;
        int tunnelNum = 0;
        int highwayNum = 0;
        String param = "qt=carnav&start=1$$$$" + beginX + "," + beginY + "$$$$$$$$$$&dest=1$$$$" + EndX + "," + EndY + "$$$$$$$$$$";
        String result = "";
        for (int i = 0;i<REQ_SUCCESS_AVER; i++) {
            result = DoHttpRequest.doGetRequest(QQ_MAP_URL, param);
            if (!StringUtils.isEmpty(result)) {
                break;
            }
        }
        result = result.replace("QQMapCallback && QQMapCallback(", "");
        String json = result.substring(0, result.length() - 1);
        //json 转换成map
        ObjectMapper objectMapper = new ObjectMapper();
        Map resultMap = objectMapper.readValue(json, Map.class);
        int distance = (Integer) resultMap.get("distance");
        //取坐标集合
        String coors = (String) resultMap.get("coors");
        String[] coorsArray = coors.split(",");
        double[] coorsArrayd = new double[coorsArray.length];
        for (int i = 0; i < coorsArray.length; i++) {
            coorsArrayd[i] = Double.parseDouble(coorsArray[i]);
        }
        //解析前向查分压缩
        for (int i = NUM_2; i < coorsArrayd.length; i++) {
            coorsArrayd[i] = coorsArrayd[i - NUM_2] + coorsArrayd[i] / DIVISOR;
        }
        //转换成经纬度
        for (int i = 0; i < coorsArrayd.length - 1;  i = i + NUM_2) {
            double[] coor = Mercator2lonLat(coorsArrayd[i], coorsArrayd[i + 1]);
            coorsArrayd[i] = coor[0];
            coorsArrayd[i + 1] = coor[1];
        }
        DecimalFormat decimalFormat = new DecimalFormat("##0.0000000000");
        int chargeCoor = 0;
        List<Map> highwayList = new ArrayList();
        List segmentList = (List) resultMap.get("segmentList");
        for (int i = 0; i < segmentList.size(); i++) {
            Map segmentMap = (Map) segmentList.get(i);
            List tipsList = (List) segmentMap.get("tips");
            if (!tipsList.isEmpty()) {
                for (int j = 0; j < tipsList.size(); j++) {
                    Map tipsMap = (Map) tipsList.get(j);
                    //取收费站坐标，判断是否属于山东省
                    int tips_type = (Integer) tipsMap.get("tips_type");
                    if (tips_type == TIPTIPE_TUNNEL) {
                        tunnelNum = +tunnelNum;
                    }
                    if (tips_type == TIPTIPE_CHARGE) {
                        String chargeName = (String) tipsMap.get("name");
                        chargeCoor = (Integer) tipsMap.get("coorStart");
                        Map highwayMap = new HashMap();
                        highwayMap.put("Point", chargeName);
                        highwayMap.put("chargeCoor", chargeCoor);
                        highwayNum += 1;
                        highwayList.add(highwayMap);
                    }
                }
            }
        }
        List sectionList = new ArrayList();
        Set provincSet = new HashSet();
        //收费站点 转换成 路段点
        for (int i = 0; i < highwayList.size() - 1; i++) {
            Map sectionMap = new HashMap();
            double section = 0;
            int startCoor = (Integer) highwayList.get(i).get("chargeCoor");
            int endCoor = (Integer) highwayList.get(i + 1).get("chargeCoor");
            int midCoor = (startCoor + endCoor) / NUM_2;
            sectionMap.put("startPoint",  highwayList.get(i).get("Point"));
            sectionMap.put("endPoint", highwayList.get(i + 1).get("Point"));
            for (int j = startCoor; j <= endCoor; j++) {
                section = section + Distance(coorsArrayd[NUM_2 * j], coorsArrayd[NUM_2 * j + 1], coorsArrayd[NUM_2 * j + NUM_2], coorsArrayd[NUM_2 * j + NUM_3]);
            }
            String province = getProvince(decimalFormat.format(coorsArrayd[NUM_2 * midCoor]), decimalFormat.format(coorsArrayd[NUM_2 * midCoor + 1]));
            provincSet.add(province);
            sectionMap.put("province", province);
            sectionMap.put("section", section);
            sectionList.add(sectionMap);
        }
        Map map = new HashMap();
        map.put("distance", distance);
        map.put("tunnelNum", tunnelNum);
        map.put("bridgeNum", bridgeNum);
        map.put("provinceNum", provincSet.size());
        map.put("highwayNum", highwayNum);
        map.put("sectionList", sectionList);
        return map;
    }

    /**
     * 获取收费站省份
     *
     * @param pointX
     * @param pointY
     * @return
     */
    public static String getProvince(String pointX, String pointY) throws IOException {
        String key = APIKEY;
        String province = null;
        String param = "location=" + pointY + "," + pointX + "&key=" + key;
        String json = DoHttpRequest.doGetRequest(GET_PROVINCE_URL, param);
        ObjectMapper objectMapper = new ObjectMapper();
        Map resultMap = objectMapper.readValue(json, Map.class);
        Map result = (Map) resultMap.get("result");
        Map address_component = (Map) result.get("address_component");
        province = (String) address_component.get("province");
        return province;
    }

    /**
     * 墨卡托转经纬度
     *
     * @param mercatorX
     * @param mercatorY
     * @return
     */
    private static double[] Mercator2lonLat(double mercatorX, double mercatorY) {
        double[] coor = new double[NUM_2];
        double longx = mercatorX / EARTH_P * radian_180;
        double latiy = mercatorY / EARTH_P * radian_180;
        latiy = radian_180 / M_PI * (NUM_2 * Math.atan(Math.exp(latiy * M_PI / radian_180)) - M_PI / NUM_2);
        coor[0] = longx;
        coor[1] = latiy;
        return coor;
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1  第一点纬度
     * @param long2 第二点经度
     * @param lat2  第二点纬度
     * @return 返回距离 单位：米
     */
    private static double Distance(double long1, double lat1, double long2,
                                   double lat2) {
        double a, b;
        lat1 = lat1 * M_PI / radian_180;
        lat2 = lat2 * M_PI / radian_180;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / radian_180;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / DOUBLE_2);
        sb2 = Math.sin(b / DOUBLE_2);
        d = NUM_2
                * EARTH_R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d / NUM_1000;
    }
}
