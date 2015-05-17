package com.manyi.point.support.dao;

import com.manyi.base.entity.OpEvent;
import com.manyi.point.bean.*;
import com.manyi.point.support.entity.GrowthLevels;
import com.manyi.point.support.entity.PointLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by Administrator on 2015/4/9.
 */
public interface PointDao {

    /**
     * 根据用户获取积分记录
     * @param serviceId
     * @return
     */
    Point getPointByUser(@Param("serviceId")String serviceId , @Param("userId") int userId);

    /**
     * 根据事件ID获取事件
     * @param eventCode
     * @return
     */
    OpEvent getEventByCode(@Param("eventCode")String eventCode);

    /**
     * 创建新的point记录
     * @param point
     * @return
     */
    int creatPoint(Point point);

    /**
     * 更新point记录
     * @param point
     * @return
     */
    int updatePoint(Point point);

    /**
     * 创建积分日志
     * @param log
     * @return
     */
    int  createPointLog(PointLog log);

    /**
     * 创建成长值日志
     * @param log
     * @return
     */
    int  creatGrowthLog(PointLog log);


    /**
     *根据条件分页查询积分
     * @param condition
     * @return
     */
    List<Point> getPoints (QueryPointCondition condition) ;

    /**
     * 查询积分日志
     * @param condition
     * @return
     */
    List<PointLogBean> queryPointLog(QueryPointLogCondition condition);

    /**
     * 查询成长值日志
     * @param condition
     * @return
     */
    List<PointLogBean> queryGrowthLog(QueryPointLogCondition condition);

    /**
     * 获取当前条件下积分总数
     * @param condition
     * @return
     */
    int getPointPageCount(QueryPointCondition condition);

    /**
     * 获取当前条件下积分日志总数
     * @param condition
     * @return
     */
    int getPointLogPageCount(QueryPointLogCondition condition);

    /**
     * 获取当前条件下积成长值日志总数
     * @param condition
     * @return
     */
    int  getGrowthLogPageCount(QueryPointLogCondition condition);

    /**
     * 删除记录
     * @param id
     */
    void deletePoint(int id);

    void deleteGrowthLog(int id);

    void deletePointLog(int id);
}
