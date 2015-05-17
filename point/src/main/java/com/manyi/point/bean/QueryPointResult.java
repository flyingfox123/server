package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2015/4/12.
 */
public class QueryPointResult extends Pagination {

    private List<Point> list ;

    public List<Point> getList() {
        return list;
    }

    public void setList(List<Point> list) {
        this.list = list;
    }
}
