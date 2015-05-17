package com.manyi.usercenter.shiro.support;

import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.shiro.ShiroFilerChainManagerService;
import com.manyi.usercenter.shiro.bean.UrlFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyufeng on 2015/5/7 0007.
 */
@Service
public class ShiroFilerChainManagerServiceImpl implements ShiroFilerChainManagerService {
    @Autowired
    private DefaultFilterChainManager filterChainManager;

    private Map<String, NamedFilterList> defaultFilterChains;

    @Override
    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
    }

    @Override
    public void initFilterChains(List<RolePerm> rolePerms) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (RolePerm rolePerm : rolePerms) {
//            String url = rolePerm.getUrl();
//            //注册roles filter
//            if (!StringUtils.isEmpty(rolePerm.getRoles())) {
//                filterChainManager.addToChain(url, "roles", rolePerm.getRoles());
//            }
//            //注册perms filter
//            if (!StringUtils.isEmpty(rolePerm.getPermissions())) {
//                filterChainManager.addToChain(url, "perms", rolePerm.getPermissions());
//            }
        }
    }
}
