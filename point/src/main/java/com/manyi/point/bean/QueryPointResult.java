package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
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
