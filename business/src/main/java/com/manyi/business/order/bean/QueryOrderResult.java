package com.manyi.business.order.bean;

import com.manyi.base.entity.Pagination;

import java.util.List;


/**
 * @Description: ETC业务实现类
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
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
