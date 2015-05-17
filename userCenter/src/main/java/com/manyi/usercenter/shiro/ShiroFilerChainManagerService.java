package com.manyi.usercenter.shiro;

import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.shiro.bean.UrlFilter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by zhangyufeng on 2015/5/7 0007.
 */
public interface ShiroFilerChainManagerService {

    @PostConstruct
    public void init();

    public void initFilterChains(List<RolePerm> rolePerms);
}
