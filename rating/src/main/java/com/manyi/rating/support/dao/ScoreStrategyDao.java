package com.manyi.rating.support.dao;


import com.manyi.rating.entity.score.GradeStrategy;
import com.manyi.rating.entity.score.GradeStrategyItem;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface ScoreStrategyDao {
    /**
     * 添加策略
     *
     * @param gradeStrategy
     * @return
     */
    int addGradeStrategy(GradeStrategy gradeStrategy);

    /**
     * 添加策略详情
     *
     * @param item
     * @return
     */
    int addGradeStrategyItem(GradeStrategyItem item);

    /**
     * 删除策略
     *
     * @param gradeStrategyID
     * @return
     */
    int deleteGradeStrategy(String gradeStrategyID);

    /**
     * 删除策略详细规则
     *
     * @param gradeStrategyID
     * @return
     */
    int delGradeStrategyItem(String gradeStrategyID);

    /**
     * 修改策略
     *
     * @param gradeStrategy
     * @return
     */
    int editGradeStrategy(GradeStrategy gradeStrategy);

    /**
     * 修改策略详情
     *
     * @param item
     * @return
     */
    int editGradeStrategyItem(GradeStrategyItem item);

    /**
     * 查询策略
     */
    List<GradeStrategy> getGradeStrategyList();

    /**
     * @param gradeStrategyID 策略id
     * @return
     */
    GradeStrategy getGradeStrategy(String gradeStrategyID);


    /**
     * 查询单个策略详情
     *
     * @param gradeStrategyID
     * @return
     */
    List<GradeStrategyItem> getGradeStrategyItem(String gradeStrategyID);
}
