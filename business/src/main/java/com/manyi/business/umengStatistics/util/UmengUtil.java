package com.manyi.business.umengStatistics.util;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description: 友盟公共类
 * @author zhaoyuxin
 * @version: 1.0.0 2015-05-18
 * @reviewer:
 */
public class UmengUtil {

    /**
     * 处理友盟返回数据的公共方法
     * @param maps
     * @return
     */
    public static List<Map> DealWithUmengResult(Map maps){
        //开始处理结果集
        List<Map> resultList = null;
        Map<String,List> data = (Map)maps.get("data");
        List dates = (List)maps.get("dates");
        String dateStr = null;
        List quotaList=null;
        Map<String,Integer> quotaMap =null;
        Map<String,List> resultMap = null;
        for(int i=0;i<dates.size();i++){
            dateStr = (String)dates.get(i);
            resultMap=new HashMap<String, List>();
            resultList=new ArrayList<Map>();
            for(Map.Entry<String, List> entry : data.entrySet()){
                quotaMap = new HashMap();
                quotaList=new ArrayList();
                List datalist = data.get(entry.getKey());
                int count = (Integer)datalist.get(i);
                quotaMap.put(entry.getKey(),count);
                quotaList.add(quotaMap);
            }
            resultMap.put(dateStr,quotaList);
            resultList.add(resultMap);
        }
        return resultList;
    }
}
