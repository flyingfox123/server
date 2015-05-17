package com.manyi.rating;


import com.manyi.rating.entity.common.RootMsg;
import com.manyi.rating.entity.score.GradeStrategy;
import com.manyi.rating.entity.score.StrategyMsg;
import com.manyi.rating.entity.score.StrategysMsg;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface ScoreStrategyService {
    /**
     * 条件获取评价策略列表
     * @return
     */
      StrategysMsg getScoreStrategys();

    /**
     *  获取特定的评价策略以及其详情
     * @param gradeStrategyID
     * @return
     */
      StrategyMsg getScoreStrategy(String gradeStrategyID);


    /**
     *  增加评价策略
     * @param strategy
     * @return
     */
     RootMsg addStrategy(GradeStrategy strategy);


    /**
     *  修改评价策略
     * @param strategy
     * @return
     */
    RootMsg editStrategy(GradeStrategy strategy);


    /**
     *  删除评价策略
     * @param gradeStrategyID
     * @return
     */
    RootMsg deleteStrategy(String gradeStrategyID);


}
