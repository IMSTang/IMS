package com.feng.project.system.post.controller;

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
import com.feng.project.system.post.domain.Post;
import com.feng.project.system.post.service.IPostService;

/**
 * 岗位信息操作处理
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/post")
public class PostController extends BaseController
{
    private String prefix = "system/post";

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Post post)
    {
        setPageInfo(post);
        List<Post> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    /**
     * 删除
     */
    @Log(title = "System Management", action = "Post - delete Post")
    @RequiresPermissions("system:post:remove")
    @RequestMapping("/remove/{postId}")
    @ResponseBody
    public JSON remove(@PathVariable("postId") Long postId)
    {
        Post post = postService.selectPostById(postId);
        if (post == null)
        {
            return JSON.error("Post does not exist");
        }
        if (postService.deletePostById(postId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:post:batchRemove")
    @Log(title = "System Management", action = "Post - batch remove Post")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = postService.batchDeletePost(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 新增岗位
     */
    @Log(title = "System Management", action = "Post - add Post")
    @RequiresPermissions("system:post:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改岗位
     */
    @Log(title = "System Management", action = "Post - edit Post")
    @RequiresPermissions("system:post:edit")
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, Model model)
    {
        Post post = postService.selectPostById(postId);
        model.addAttribute("post", post);
        return prefix + "/edit";
    }

    /**
     * 保存岗位
     */
    @Log(title = "System Management", action = "Post - save Post")
    @RequiresPermissions("system:post:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Post post)
    {
        if (postService.savePost(post) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}
