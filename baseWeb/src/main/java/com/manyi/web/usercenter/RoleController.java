package com.manyi.web.usercenter;

import com.manyi.bean.JsonResult;
import com.manyi.usercenter.role.RoleService;
import com.manyi.usercenter.role.bean.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ZhangYuFeng on 2015/6/15 0015,15:11.
 * @Description:
 * @version: 1.0.0
 * @reviewer:
 */

@Controller
@RequestMapping("/admin/sysRole")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     *
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public List<SysRole> testSelect(HttpServletRequest request) {
        String newToken = (String)request.getAttribute("newToken");
        List<SysRole> sysSysUser = roleService.findAllRoles();
        return sysSysUser;
    }

    /**
     * 添加角色
     *
     * @param sysRole
     */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonResult addRole(@Valid @RequestBody SysRole sysRole, Errors errors) {
        if (null != sysRole) {
            if (errors.hasErrors()) {
                return new JsonResult(errors);
            } else {
                try {
                    boolean res = roleService.addRole(sysRole);
                    if (res) {
                        return new JsonResult("success", "操作成功");
                    }
                } catch (Exception e) {
                    logger.error("",e);
                }
                return new JsonResult("failure", "服务器出错啦");
            }
        }
        return new JsonResult("failure", "参数填写不正确");
    }

    /**
     * 更新角色
     *
     * @param sysRole
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult testUpdate(@Valid @RequestBody SysRole sysRole, Errors errors) {
        JsonResult jsonResult = new JsonResult();
        if (null != sysRole) {
            if (errors.hasErrors()) {
                jsonResult.setMessage("参数填写不正确");
            } else {
                try {
                    boolean res = roleService.editRole(sysRole);
                    if (res) {
                        jsonResult.setResult("success");
                        jsonResult.setMessage("操作成功");
                    }
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
        }
        return jsonResult;
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult testDelete(Long id) {
        JsonResult jsonResult = new JsonResult();
        if (null != id) {
            try {
                boolean res = roleService.deleteRole(id);
                if (res) {
                    jsonResult.setResult("success");
                    jsonResult.setMessage("操作成功");
                }
            } catch (Exception e) {
                logger.error("",e);
            }
        }
        return jsonResult;
    }

}
