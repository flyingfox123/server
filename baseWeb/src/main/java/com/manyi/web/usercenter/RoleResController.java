package com.manyi.web.usercenter;

import com.manyi.bean.JsonResult;
import com.manyi.usercenter.role.RoleResService;
import com.manyi.usercenter.role.bean.GrantAuth;
import com.manyi.usercenter.role.bean.RoleResourceTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Magic on 2015/2/27.
 */

@Controller
@RequestMapping("/admin/sysRoleRes")
public class RoleResController {

    private static Logger logger = LoggerFactory.getLogger(RoleResController.class);

    @Autowired
    private RoleResService roleResService;

    /**
     * 获取该角色的权限
     *
     * @param roleId
     */
    @RequestMapping("/findResByRoleId")
    @ResponseBody
    public List findResByRoleId(Long roleId) {
        List<RoleResourceTreeNode> list = null;
        try {
            // 获取组装后的树节点
            list = roleResService.findAllRoleResoByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 为当前角色授权
     * @return
     */
    @RequestMapping("/grantAuth")
    @ResponseBody
    public JsonResult grantAuth(@RequestBody GrantAuth grantAuth) {
        System.out.println(grantAuth.getCheckedNodes() + "\t" + grantAuth.getRoleId());
        try {
            roleResService.grantAuth(grantAuth);
        } catch (Exception e) {
            e.printStackTrace();
            new JsonResult(JsonResult.FAILURE, e.getClass() + "\t" + e.getMessage());
        }
        return new JsonResult(JsonResult.SUCCESS,"授权成功");
    }
}
