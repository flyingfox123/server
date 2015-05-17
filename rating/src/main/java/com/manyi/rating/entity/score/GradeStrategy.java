package com.manyi.rating.entity.score;

import java.util.List;

/**
 * Created by Administrator on 2015/3/18.
 */
public class GradeStrategy {

    private long id;

    private String gradeStrategyID;

    private String  scoreRule;

    private String gradeStrategyName;

    private List<GradeStrategyItem> strategyItem;



    public String getGradeStrategyID() {
        return gradeStrategyID;
    }

    public void setGradeStrategyID(String gradeStrategyID) {
        this.gradeStrategyID = gradeStrategyID;
    }

    public String getScoreRule() {
        return scoreRule;
    }

    public void setScoreRule(String scoreRule) {
        this.scoreRule = scoreRule;
    }

    public String getGradeStrategyName() {
        return gradeStrategyName;
    }

    public void setGradeStrategyName(String gradeStrategyName) {
        this.gradeStrategyName = gradeStrategyName;
    }

    public List<GradeStrategyItem> getStrategyItem() {
        return strategyItem;
    }

    public void setStrategyItem(List<GradeStrategyItem> strategyItem) {
        this.strategyItem = strategyItem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeStrategy)) return false;

        GradeStrategy that = (GradeStrategy) o;

        if (id != that.id) return false;
        if (gradeStrategyID != null ? !gradeStrategyID.equals(that.gradeStrategyID) : that.gradeStrategyID != null)
            return false;
        if (gradeStrategyName != null ? !gradeStrategyName.equals(that.gradeStrategyName) : that.gradeStrategyName != null)
            return false;
        if (scoreRule != null ? !scoreRule.equals(that.scoreRule) : that.scoreRule != null) return false;
        if (strategyItem != null ? !strategyItem.equals(that.strategyItem) : that.strategyItem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gradeStrategyID != null ? gradeStrategyID.hashCode() : 0);
        result = 31 * result + (scoreRule != null ? scoreRule.hashCode() : 0);
        result = 31 * result + (gradeStrategyName != null ? gradeStrategyName.hashCode() : 0);
        result = 31 * result + (strategyItem != null ? strategyItem.hashCode() : 0);
        return result;
    }
}
