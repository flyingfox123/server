package com.manyi.webext.order.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/6/15.
 */
public class Test {


    private Integer state;

    private String amount;

    private String test;

    private List<Test1> list ;

    public List<Test1> getList() {
        return list;
    }

    public void setList(List<Test1> list) {
        this.list = list;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
