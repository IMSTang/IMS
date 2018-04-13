package com.feng.project.purchase.vendor.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.purchase.vendor.service.IVendorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Vendor信息操作处理
 * 
 * @author feng
 */
@Controller
@RequestMapping("/purchase/vendor")
public class VendorController extends BaseController
{
    private String prefix = "purchase/vendor";

    @Autowired
    private IVendorService vendorService;

    @RequiresPermissions("purchase:vendor:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/vendor";
    }

    @RequiresPermissions("purchase:vendor:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Vendor vendor)
    {
        setPageInfo(vendor);
        List<Vendor> list = vendorService.selectVendorList(vendor);
        return getDataTable(list);
    }

    /**
     * 删除
     */
    @Log(title = "Purchase Management", action = "Vendor Management - remove Vendor")
    @RequiresPermissions("purchase:vendor:remove")
    @RequestMapping("/remove/{vendorId}")
    @ResponseBody
    public JSON remove(@PathVariable("vendorId") Long vendorId)
    {
        Vendor vendor = vendorService.selectVendorById(vendorId);
        if (vendor == null)
        {
            return JSON.error("Vendor no exist");
        }
        if (vendorService.deleteVendorById(vendorId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("purchase:vendor:batchRemove")
    @Log(title = "Purchase Management", action = "Vendor Management - batch Remove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = vendorService.batchDeleteVendor(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 新增Vendor
     */
    @Log(title = "Purchase Management", action = "Vendor Management - add Vendor")
    @RequiresPermissions("purchase:vendor:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改Vendor
     */
    @Log(title = "Purchase Management", action = "Vendor Management - edit Vendor")
    @RequiresPermissions("purchase:vendor:edit")
    @GetMapping("/edit/{vendorId}")
    public String edit(@PathVariable("vendorId") Long vendorId, Model model)
    {
        Vendor vendor = vendorService.selectVendorById(vendorId);
        model.addAttribute("vendor", vendor);
        return prefix + "/edit";
    }

    /**
     * 保存Vendor
     */
    @Log(title = "Purchase Management", action = "Vendor Management - save Vendor")
    @RequiresPermissions("purchase:vendor:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Vendor vendor)
    {
        if (vendorService.saveVendor(vendor) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}