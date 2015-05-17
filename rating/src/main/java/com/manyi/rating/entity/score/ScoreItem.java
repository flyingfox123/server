package com.manyi.rating.entity.score;

/**
 * Created by Administrator on 2015/3/17.
 */
public class ScoreItem {

    private long id;
    private long scoreid;
    private long itemId;
    private float weight;
    private int itemScore;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScoreid() {
        return scoreid;
    }

    public void setScoreid(long scoreid) {
        this.scoreid = scoreid;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getItemScore() {
        return itemScore;
    }

    public void setItemScore(int itemScore) {
        this.itemScore = itemScore;
    }
}
