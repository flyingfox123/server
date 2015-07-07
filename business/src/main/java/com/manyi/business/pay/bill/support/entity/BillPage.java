package com.manyi.business.pay.bill.support.entity;

import com.manyi.base.entity.Pagination;

import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/23 0023,19:13.
 * @Description:
 * @reviewer:
 */
public class BillPage extends Pagination {
    List<Bill> list;

    public List<Bill> getList() {
        return list;
    }

    public void setList(List<Bill> list) {
        this.list = list;
    }
}
