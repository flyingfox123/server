package com.manyi.point;

/**
 * Created by liukaihua on 2015/6/9.
 */

import com.manyi.base.exception.BusinessException;
import com.manyi.point.bean.*;
import com.manyi.point.support.entity.PointLog;

import java.util.*;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public interface PointService {

//     int addPointAndGrow(PointParam request) throws BusinessException;
    /**
     * 增加积分
     */
    int addPoint(PointParam request) throws BusinessException;
    /**
     * 增加成长值
     * */
//    int addGrowth(PointParam request) throws BusinessException;
    /**
     *使用积分
     */
    int usePoint(UsePointParam usePoint) throws BusinessException;
    /**
     * 更新积分
     * */
    int updatePoint(UpdatePointParam request) throws BusinessException;
    /**
     * 更新成长值
     * */
//    int updateGrowth(UpdateGrowthParam  request) throws BusinessException;
    /**
     * 查询用户积分、成长值详情
     * */
    Point queryUserPoint(String serviceId , long userId);

    /**
     * 条件查询积分、成长值详情
     * */
    QueryPointResult queryPoints(QueryPointCondition condition);


     /**
      * 查询用户积分日志
      * */
     PointLogResult queryUserPointLog(QueryPointLogCondition condition);

     /**
      * 查询用户成长值日志
      * */
   //  PointLogResult queryUserGrowthLog(QueryPointLogCondition condition);


}
