package com.manyi.usercenter.shiro;


import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.shiro.bean.UrlFilter;

import java.util.List;

/**
 * <p>User: zhangyufeng
 * <p>Date: 15-5-7
 * <p>Version: 1.0
 */
public interface UrlFilterService {

    public RolePerm createUrlFilter(RolePerm rolePerm);
    public RolePerm updateUrlFilter(RolePerm rolePerm);
    public void deleteUrlFilter(Long urlFilterId);

    public RolePerm findOne(Long urlFilterId);
    public List<RolePerm> findAll();
}
