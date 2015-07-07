package com.manyi.web.usercenter;

import com.manyi.bean.JsonResult;
import com.manyi.usercenter.permission.PermissionService;
import com.manyi.usercenter.permission.bean.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/15 0015,15:11.
 * @Description:
 * @version: 1.0.0
 * @reviewer:
 */
@Controller
@RequestMapping("/admin/sysResource")
public class PermissionController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     *
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public List<Permission> findAllPermission() {

        List<Permission> allResources = permissionService.findAllPermission();
        return allResources;
    }

    /**
     * 添加权限
     *
     * @param permission
     */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonResult testInsert(@Valid @RequestBody Permission permission, Errors errors) {

        if (null != permission) {
            if (errors.hasErrors()) {
                return new JsonResult(errors);
            } else {
                try {
                    permissionService.addPermission(permission);
                    return new JsonResult("success", "操作成功");
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
        }
        return new JsonResult("failure", "参数填写不正确");
    }

    /**
     * 修改权限
     *
     * @param permission
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult updatePermission(@Valid @RequestBody Permission permission, Errors errors) {
        if (null != permission) {
            if (errors.hasErrors()) {
                return new JsonResult(errors);
            } else {
                try {
                    permissionService.updatePermission(permission);
                    return new JsonResult("success", "操作成功");
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
        }
        return new JsonResult("failure", "参数填写不正确");
    }

    /**
     * 删除权限
     *
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult deletePermission(Long id) {
        if (null != id) {
            try {
                permissionService.deletePermission(id);
                return new JsonResult("success", "操作成功");
            } catch (Exception e) {
                logger.error("",e);
            }
        }
        return new JsonResult("failure", "验证不通过，无法删除");
    }
}
