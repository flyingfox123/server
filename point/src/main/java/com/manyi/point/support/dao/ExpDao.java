package com.manyi.point.support.dao;

import com.manyi.point.bean.PointEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface ExpDao {
//
//    /**
//     *
//     * @param code
//     * @return
//     */
//    List<DimensionExp>  getExpList(String code);


    /**
     * 根据事件ID获取事件
     * @param eventCode
     * @return
     */
    PointEvent getPointEventByCode(@Param("eventCode")String eventCode);





}
