package com.manyi.rating.entity.score;

/**
 * Created by Administrator on 2015/3/18.
 */
public class GradeStrategyItem {

    private long id;
    private String gradeStrategyID;
    private String name;
    private float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGradeStrategyID() {
        return gradeStrategyID;
    }

    public void setGradeStrategyID(String gradeStrategyID) {
        this.gradeStrategyID = gradeStrategyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeStrategyItem)) return false;

        GradeStrategyItem that = (GradeStrategyItem) o;

        if (id != that.id) return false;
        if (Float.compare(that.weight, weight) != 0) return false;
        if (gradeStrategyID != null ? !gradeStrategyID.equals(that.gradeStrategyID) : that.gradeStrategyID != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gradeStrategyID != null ? gradeStrategyID.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        return result;
    }
}
