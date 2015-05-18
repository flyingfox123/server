package com.manyi.business.order.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * Created by Administrator on 2015/5/5.
 */
public class QueryOrderItemResult  extends Pagination{

    private List<OrderItem> list;

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }
}
