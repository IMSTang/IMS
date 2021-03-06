package com.feng.project.system.user.controller;

import java.util.List;

import com.feng.common.utils.security.ShiroUtils;
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
import com.feng.project.system.post.domain.Post;
import com.feng.project.system.post.service.IPostService;
import com.feng.project.system.role.domain.Role;
import com.feng.project.system.role.service.IRoleService;
import com.feng.project.system.user.domain.User;
import com.feng.project.system.user.service.IUserService;

/**
 * 用户信息
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        setPageInfo(user);
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "System Management", action = "Users -修改用户")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        List<Post> posts = postService.selectPostsByUserId(userId);
        model.addAttribute("roles", roles);
        model.addAttribute("posts", posts);
        model.addAttribute("user", user);
        return prefix + "/edit";
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "System Management", action = "Users - add User")
    @GetMapping("/add")
    public String add(Model model)
    {
        List<Role> roles = roleService.selectRoleAll();
        List<Post> posts = postService.selectPostAll();
        model.addAttribute("roles", roles);
        model.addAttribute("posts", posts);
        return prefix + "/add";
    }
    
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "System Management", action = "Users - to reset password")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        model.addAttribute("user", user);
        return prefix + "/resetPwd";
    }
    
    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "System Management", action = "Users - reset password")
    @PostMapping("/resetPwd")
    @ResponseBody
    public JSON resetPwd(User user)
    {
        int rows = userService.updateUser(user);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "System Management", action = "Users - delete User")
    @RequestMapping("/remove/{userId}")
    @ResponseBody
    public JSON remove(@PathVariable("userId") Long userId)
    {
        User user = userService.selectUserById(userId);
        if (user == null)
        {
            return JSON.error("User does not exist");
        }
        if (userService.deleteUserById(userId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:user:batchRemove")
    @Log(title = "System Management", action = "Users - batch remove Users")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = userService.batchDeleteUser(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 保存
     */
    @RequiresPermissions("system:user:save")
    @Log(title = "System Management", action = "Users - save User")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(User user)
    {

        if (userService.saveUser(user) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkNameUnique")
    @ResponseBody
    public String checkNameUnique(User user)
    {

        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkNameUnique(user.getLoginName());
        }
        return uniqueFlag;
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree")
    public String selectDeptTree()
    {
        return prefix + "/tree";
    }


    @PostMapping("/changePWD")
    @ResponseBody
    public JSON checkNameUnique(String newPWD)
    {
        int result = 0;

            result = userService.changePWD(newPWD);
       if(result<=0){
           return JSON.error("change Password fail") ;
       }
        return JSON.ok() ;
    }
}