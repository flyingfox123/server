package com.manyi.usercenter.permission.support.dao;

import com.manyi.usercenter.permission.bean.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Magic on 2015/1/7.
 */
public interface PermissionDao {

    /**
     * 获取所有权限
     *
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 取得指定权限
     * @param id
     * @param name
     * @return
     */
    List<Permission> findPermission(@Param("id")String id,@Param("name")String name);

    /**
     * 添加权限
     *
     * @return
     */
    int addPermission(Permission permission);

    /**
     * 修改权限
     *
     * @return
     */
    int updatePermission(Permission permission);

    /**
     * 删除权限
     *
     * @return
     */
    int deletePermission(Long id);

//    private static Logger logger = LoggerFactory.getLogger(ResourceDao.class);
//
//    @Autowired
//    private ResourceMapper resourceMapper;
//
//    public List<SysResource> findAllResources() {
//        List<SysResource> list = null;
//        list = resourceMapper.findAllResources();
//        if (null != list) {
//            return list;
//        }
//        return null;
//    }
//
//    public int addResource(SysResource sysResource) {
//        int res = resourceMapper.addResource(sysResource);
//        return res;
//    }
//
//    public int editResource(SysResource sysResource) {
//        int res = resourceMapper.editResource(sysResource);
//        return res;
//    }
//
//    public int deleteResource(Long id) {
//        int res = resourceMapper.deleteResource(id);
//        return res;
//    }
}
