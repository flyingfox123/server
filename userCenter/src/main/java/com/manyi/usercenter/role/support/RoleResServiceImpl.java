package com.manyi.usercenter.role.support;

import com.manyi.usercenter.permission.support.dao.PermissionDao;
import com.manyi.usercenter.role.support.dao.RoleResDao;
import com.manyi.usercenter.role.bean.GrantAuth;
import com.manyi.usercenter.role.bean.RoleResourceTreeNode;
import com.manyi.usercenter.permission.bean.Permission;
import com.manyi.usercenter.role.bean.SysRoleResRel;
import com.manyi.usercenter.role.RoleResService;
import com.manyi.usercenter.shiro.util.CurrentUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* Created by Magic on 2015/2/27.
*/

@Service("roleResService")
public class RoleResServiceImpl implements RoleResService {

    private static Logger logger = LoggerFactory.getLogger(RoleResServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RoleResDao roleResDao;

    /**
     * 根据角色ID获取该角色的所有权限id
     *
     * @param roleId 角色ID
     * @return Set<String> 资源ID集合
     */
    public Set<String> findByRoleId(Long roleId) {

        Set<String> resIds = new HashSet<String>();

        List<SysRoleResRel> roleResList = roleResDao.getAllResByRoleId(roleId);

        for (SysRoleResRel item : roleResList) {
            resIds.add(item.getResId());
        }
        return resIds;
    }

    @Override
    public List<RoleResourceTreeNode> findAllRoleResoByRoleId(Long roleId) {
        // 根据角色ID获取该角色的所有权限id
        Set<String> set = this.findByRoleId(roleId);

        // 获取所有的资源
        List<Permission> list = permissionDao.findAllPermission();

        List<RoleResourceTreeNode> listTreeNode = new ArrayList<RoleResourceTreeNode>();
        for (Permission sysResource : list) {
            RoleResourceTreeNode treeNode = new RoleResourceTreeNode();
            treeNode.setId(String.valueOf(sysResource.getId()));
            treeNode.setName(sysResource.getName());
            treeNode.setOpen(false);
            treeNode.setpId(String.valueOf(sysResource.getPid()));
            treeNode.setRoleId(roleId);
            if (set.contains(sysResource.getId())) {
                treeNode.setChecked(true);
            } else {
                treeNode.setChecked(false);
            }
            listTreeNode.add(treeNode);
        }
        return listTreeNode;
    }

    /**
     * 为当前的角色授权
     *
     * @param grantAuth
     * @return boolean
     */
    @Override
    public boolean grantAuth(GrantAuth grantAuth) {
        String checkList = grantAuth.getCheckedNodes();
        if (null != checkList && checkList.length() > 0 && !"".equals(checkList)) {
            if ("un".equals(checkList)) {
                return false;
            }

            String[] strCheckList = checkList.split(",");

            // 遍历解析数据
            for (int i = 0; i < strCheckList.length; i++) {

                String[] oneNode = strCheckList[i].split("_");

                SysRoleResRel sysRoleResRel = new SysRoleResRel();
                // 获取权限id
                sysRoleResRel.setResId(oneNode[0]);
                // 获取角色id
                sysRoleResRel.setRoleId(grantAuth.getRoleId());

                // 获取选中状态并判断如果选中进行新增操作
                if (Boolean.valueOf(oneNode[1])) {
                    List<SysRoleResRel> list = findSysRoleRes(sysRoleResRel);
                    if (list.size() == 0) {
                        Long userId = CurrentUserUtils.getCurrentUserId();
                        sysRoleResRel.setCreator(userId);
                        sysRoleResRel.setCreateTime(new Date());
                        sysRoleResRel.setModifier(userId);
                        sysRoleResRel.setModifyTime(new Date());
                        grantResourceForRole(sysRoleResRel);
                    }
                } else { // 如果未选中，进行删除操作
                    deleteRoleResRel(sysRoleResRel);
                }
            }
        }
        return true;
    }

    /**
     * 根据组装条件查询角色权限表
     *
     * @param sysRoleResRel
     * @return List<SysRoleResRel>角色权限集合
     */
    public List<SysRoleResRel> findSysRoleRes(SysRoleResRel sysRoleResRel) {
        return roleResDao.findByRoleRes(sysRoleResRel);
    }

    /**
     * 为角色添加权限
     *
     * @param sysRoleResRel 角色权限实体
     */
    public void grantResourceForRole(SysRoleResRel sysRoleResRel) {
        roleResDao.addRoleRes(sysRoleResRel);
    }

    /**
     * 根据组装条件删除角色权限
     *
     * @return int 成功与否标识
     */
    public void deleteRoleResRel(SysRoleResRel sysRoleResRel) {
        roleResDao.deleteByRoleRes(sysRoleResRel);
    }

}
