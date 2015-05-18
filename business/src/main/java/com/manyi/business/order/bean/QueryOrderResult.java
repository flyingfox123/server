package com.manyi.business.order.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2015/5/5.
 */
public class QueryOrderResult extends Pagination{

   private  List<Order>  list;

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }
}
