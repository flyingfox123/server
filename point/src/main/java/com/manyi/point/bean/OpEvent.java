package com.manyi.point.bean;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class OpEvent {

    private int id;
    private String eventCode;
    private String name;
    private char type ;
    private String description;
    private int eventPoint;
    private int eventGrowth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(int eventPoint) {
        this.eventPoint = eventPoint;
    }

    public int getEventGrowth() {
        return eventGrowth;
    }

    public void setEventGrowth(int eventGrowth) {
        this.eventGrowth = eventGrowth;
    }
}
