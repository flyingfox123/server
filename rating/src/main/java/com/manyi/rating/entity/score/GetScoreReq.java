package com.manyi.rating.entity.score;


import com.manyi.rating.entity.common.RootQequest;

import java.sql.Date;

/**
 * Created by Administrator on 2015/3/20.
 */
public class GetScoreReq extends RootQequest {

    private long id;

    private String tradeOrder;

    private String graderID;

    private String gradedID;

    private String commodityID;

    private String  gradeStrategyID;

    private String channelID;

    private Date evaluateTime;

    private String gradeStrategyName;

    private String scoreRule;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTradeOrder() {
        return tradeOrder;
    }

    public void setTradeOrder(String tradeOrder) {
        this.tradeOrder = tradeOrder;
    }

    public String getGraderID() {
        return graderID;
    }

    public void setGraderID(String graderID) {
        this.graderID = graderID;
    }

    public String getGradedID() {
        return gradedID;
    }

    public void setGradedID(String gradedID) {
        this.gradedID = gradedID;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public String getGradeStrategyID() {
        return gradeStrategyID;
    }

    public void setGradeStrategyID(String gradeStrategyID) {
        this.gradeStrategyID = gradeStrategyID;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getGradeStrategyName() {
        return gradeStrategyName;
    }

    public void setGradeStrategyName(String gradeStrategyName) {
        this.gradeStrategyName = gradeStrategyName;
    }

    public String getScoreRule() {
        return scoreRule;
    }

    public void setScoreRule(String scoreRule) {
        this.scoreRule = scoreRule;
    }
}
