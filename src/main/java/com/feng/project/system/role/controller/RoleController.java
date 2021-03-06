package com.feng.project.system.role.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.system.role.domain.Role;
import com.feng.project.system.role.service.IRoleService;

/**
 * 角色信息
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController
{

    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;
    
    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Role role)
    {
        setPageInfo(role);
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }
    
    /**
     * 新增角色
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "System Management", action = "Roles - add Role")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "System Management", action = "Roles - edit Role")
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, Model model)
    {
        Role role = roleService.selectRoleById(roleId);
        model.addAttribute("role", role);
        return prefix + "/edit";
    }

    /**
     * 保存角色
     */
    @RequiresPermissions("system:role:save")
    @Log(title = "System Management", action = "Roles - save Role")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Role role)
    {
        if (roleService.saveRole(role) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:role:remove")
    @Log(title = "System Management", action = "Roles - delete Role")
    @RequestMapping("/remove/{roleId}")
    @ResponseBody
    public JSON remove(@PathVariable("roleId") Long roleId)
    {
        Role role = roleService.selectRoleById(roleId);
        if (role == null)
        {
            return JSON.error("Role does not exist");
        }
        if (roleService.deleteRoleById(roleId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:role:batchRemove")
    @Log(title = "System Management", action = "Roles - batch remove Roles")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = roleService.batchDeleteRole(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

}