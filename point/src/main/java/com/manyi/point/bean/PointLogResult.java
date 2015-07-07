package com.manyi.point.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class PointLogResult extends Pagination{

   private List<PointLogBean>   list ;

    private long totalPoint;

    public long getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(long totalPoint) {
        this.totalPoint = totalPoint;
    }

    public List<PointLogBean> getList() {
        return list;
    }

    public void setList(List<PointLogBean> list) {
        this.list = list;
    }
}
