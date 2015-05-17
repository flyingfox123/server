package com.manyi.usercenter.role.support.dao;

import com.manyi.usercenter.role.bean.SysRoleResRel;

import java.util.List;

/**
 * Created by Magic on 2015/2/27.
 */

public interface RoleResDao {

    /**
     * 根据角色ID获取该角色的所有权限id
     * @return
     */
    List<SysRoleResRel> getAllResByRoleId(Long roleId);

    /**
     * 通过角色id，资源id查询关联表中的关联关系
     * @param sysRoleResRel
     * @return
     */
    List<SysRoleResRel> findByRoleRes(SysRoleResRel sysRoleResRel);

    /**
     * 添加角色资源关联关系
     * @param sysRoleResRel
     * @return
     */
    int addRoleRes(SysRoleResRel sysRoleResRel);

    /**
     * 通过角色id，资源id删除关联关系
     * @param sysRoleResRel
     * @return
     */
    int deleteByRoleRes(SysRoleResRel sysRoleResRel);

//    private static Logger logger = LoggerFactory.getLogger(RoleResDao.class);

//    @Autowired
//    private RoleResMapper roleResMapper;

//    /**
//     * 根据角色ID获取该角色的所有权限id
//     * @param roleId
//     * @return
//     */
//    public List<SysRoleResRel> findResListByRoleId(Long roleId) {
//
//        List<SysRoleResRel> list = roleResMapper.findResListByRoleId(roleId);
//        return list;
//    }
//
//    /**
//     * 通过角色id，资源id查询关联表中的关联关系
//     * @param sysRoleResRel
//     * @return
//     */
//    public List<SysRoleResRel> findByRoleRes(SysRoleResRel sysRoleResRel) {
//        return roleResMapper.findByRoleRes(sysRoleResRel);
//    }
//
//    /**
//     * 添加角色资源关联关系
//     * @param sysRoleResRel
//     * @return
//     */
//    public int addRoleRes(SysRoleResRel sysRoleResRel) {
//        return roleResMapper.addRoleRes(sysRoleResRel);
//    }
//
//    /**
//     * 通过角色id，资源id删除关联关系
//     * @param sysRoleResRel
//     * @return
//     */
//    public int deleteByRoleRes(SysRoleResRel sysRoleResRel) {
//        return roleResMapper.deleteByRoleRes(sysRoleResRel);
//    }
}
