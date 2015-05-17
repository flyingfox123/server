package com.manyi.base.entity;

/**
 * Created by Administrator on 2015/4/8.
 */
public class Pagination {

    //分页起点
    private int pageNum;
    //分页大小
    private int pageSize;
    //分页数量
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
