package com.feng.project.purchase.vendor.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.purchase.vendor.domain.VendorIdName;
import com.feng.project.purchase.vendor.service.IVendorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/search_name")
    @ResponseBody
    public List<VendorIdName> search_name(HttpServletRequest request, HttpServletResponse response)
    {
        String vName = request.getParameter("searchValue");
        List<VendorIdName> list = vendorService.selectVendorIdName(vName);

        System.out.println(list);
        return list;
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
     * check vendor Name Unique
     * @param vendor
     * @return 1 exit,0 not exit
     */
    @PostMapping("/checkNameUnique")
    @ResponseBody
    public String  checkNameUnique(Vendor vendor){
        String flag="0";
        if(vendor != null){
            flag = vendorService.checkNameUnique(vendor.getVendorName());
        }
        return  flag;

    }
    /**
     * 新增Vendor
     */
    @RequiresPermissions("purchase:vendor:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改Vendor
     */
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
