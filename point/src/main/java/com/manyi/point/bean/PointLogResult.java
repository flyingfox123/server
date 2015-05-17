package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2015/4/12.
 */
public class PointLogResult extends Pagination{

   private List<PointLogBean>   list ;

    public List<PointLogBean> getList() {
        return list;
    }

    public void setList(List<PointLogBean> list) {
        this.list = list;
    }
}
