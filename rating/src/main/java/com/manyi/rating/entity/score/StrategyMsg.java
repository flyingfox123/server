package com.manyi.rating.entity.score;


import com.manyi.rating.entity.common.RootMsg;

/**
 * Created by Administrator on 2015/3/18.
 */
public class StrategyMsg extends RootMsg {
    private GradeStrategy strategy;

    public GradeStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(GradeStrategy strategy) {
        this.strategy = strategy;
    }
}
