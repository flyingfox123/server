package com.manyi.usercenter.shiro.support;

import com.manyi.usercenter.role.bean.RolePerm;
import com.manyi.usercenter.role.support.dao.RolePermissionDao;
import com.manyi.usercenter.shiro.ShiroFilerChainManagerService;
import com.manyi.usercenter.shiro.UrlFilterService;
import com.manyi.usercenter.shiro.bean.UrlFilter;
import com.manyi.usercenter.shiro.support.dao.UrlFilterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>User: zhangyufeng
 * <p>Date: 15-5-7
 * <p>Version: 1.0
 */
@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private ShiroFilerChainManagerService shiroFilerChainManager;

    @Override
    public RolePerm createUrlFilter(RolePerm rolePerm) {
        //urlFilterDao.createUrlFilter(urlFilter);
        initFilterChain();
        return rolePerm;
    }



    @Override
    public RolePerm updateUrlFilter(RolePerm rolePerm) {
        //urlFilterDao.updateUrlFilter(urlFilter);
        initFilterChain();
        return rolePerm;
    }

    @Override
    public void deleteUrlFilter(Long urlFilterId) {
        //urlFilterDao.deleteUrlFilter(urlFilterId);
        initFilterChain();
    }

    @Override
    public RolePerm findOne(Long urlFilterId) {
//        return urlFilterDao.findOne(urlFilterId);
        return null;
    }

    @Override
    public List<RolePerm> findAll() {
        return rolePermissionDao.findRolePermission(null, null);
    }

    @PostConstruct
    public void initFilterChain() {
        shiroFilerChainManager.initFilterChains(findAll());
    }

}
