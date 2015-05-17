package com.manyi.usercenter.permission.support;

import com.manyi.usercenter.permission.support.dao.PermissionDao;
import com.manyi.usercenter.permission.bean.Permission;
import com.manyi.usercenter.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2015/4/11.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAllPermission() {
        return permissionDao.findAllPermission();
    }

    @Override
    public List<Permission> findPermission(String id, String name) {
        return permissionDao.findPermission(id,name);
    }

    @Override
    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionDao.deletePermission(id);
    }

    /**
     * 组装sysResource实体
     *
     * @param flag 1用于创建,其他值用于更新
     * @return
     */
//    private Permission bindResource(Permission sysResource, Integer flag) {
//        if (flag == 1) {
//            // 获取父级权限资源的级别，子级+1
//            if (null != sysResource.getLeve()) {
//                sysResource.setLeve(sysResource.getLeve() + 1);
//            }
//            sysResource.setId(buildResourceId(sysResource.getPid()));
//            sysResource.setCreateTime(new Date());
//            sysResource.setStatus();
//            sysResource.setIsleaf((short)0);
//            sysResource.setCreator(CurrentUserUtils.getCurrentUserId());
//        }
//        sysResource.setModifier(CurrentUserUtils.getCurrentUserId());
//        sysResource.setModifyTime(new Date());
//        return sysResource;
//    }

    /**
     * 构建resourceId
     *
     * @param pid
     * 		父级节点的id
     * @return
     */
//    private String buildResourceId(String pid) {
//        // 获得最大的子资源ID
//        String maxChildResourceId = permissionDao.findMaxChildPermissionIdByPid(pid);
//        String tempId = null;
//        String resourceId = null;
//        if(null != maxChildResourceId) {
//            tempId = maxChildResourceId.toString().substring(pid.length());
//            Integer newId = Integer.parseInt(tempId) + 1;
//            if (newId < 10) {
//                resourceId = "0" + newId;
//            } else {
//                resourceId = newId.toString();
//            }
//            resourceId = pid + resourceId;
//        }
//        return resourceId;
//    }
}
