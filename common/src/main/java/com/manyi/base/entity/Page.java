package com.manyi.base.entity;


/**
 * @Description:
 * @author wangpengfei
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class Page {

    private static final int TEN = 10;

    private static final int ZERO = 0;

    private static final int ONE = 1;

    // 当前页(默认从第1页开始)
    private int currentPage;
    // 每页显示的条数
    private int pageSize = TEN;
    // 总条数
    private int sizeCount;
    // 分页起点
    private int pageStart = ZERO;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSizeCount() {
        return sizeCount;
    }

    public void setSizeCount(int sizeCount) {
        this.sizeCount = sizeCount;
    }

    public int getPageStart() {
        if(currentPage == 0) {
            this.currentPage = ONE;
        }
        return (this.currentPage-1) * this.pageSize;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

}
