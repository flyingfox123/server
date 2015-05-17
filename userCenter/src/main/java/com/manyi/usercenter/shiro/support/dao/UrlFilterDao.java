package com.manyi.usercenter.shiro.support.dao;

import com.manyi.usercenter.shiro.bean.UrlFilter;

import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/7 0007.
 */
public interface UrlFilterDao {
    public UrlFilter createUrlFilter(UrlFilter urlFilter);
    public UrlFilter updateUrlFilter(UrlFilter urlFilter);
    public void deleteUrlFilter(Long urlFilterId);

    public UrlFilter findOne(Long urlFilterId);
    public List<UrlFilter> findAll();
}
