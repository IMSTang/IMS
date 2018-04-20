package com.feng.project.purchase.demand.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.purchase.demand.domain.Demand;
import com.feng.project.purchase.demand.service.IDemandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Demand信息操作处理
 * 
 * @author feng
 */
@Controller
@RequestMapping("/purchase/demand")
public class DemandController extends BaseController
{
    private String prefix = "purchase/demand";

    @Autowired
    private IDemandService demandService;

    @RequiresPermissions("purchase:demand:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/demand";
    }

    @RequiresPermissions("purchase:demand:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Demand demand)
    {
        setPageInfo(demand);
        List<Demand> list = demandService.selectDemandList(demand);
        return getDataTable(list);
    }

    /**
     * 删除
     */
    @Log(title = "Purchase Management", action = "Demand Management - remove Demand")
    @RequiresPermissions("purchase:demand:remove")
    @RequestMapping("/remove/{demandId}")
    @ResponseBody
    public JSON remove(@PathVariable("demandId") Long demandId)
    {
        Demand demand = demandService.selectDemandById(demandId);
        if (demand == null)
        {
            return JSON.error("Demand no exist");
        }
        if (demandService.deleteDemandById(demandId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("purchase:demand:batchRemove")
    @Log(title = "Purchase Management", action = "Demand Management - batch Remove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = demandService.batchDeleteDemand(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 新增Demand
     */
    @RequiresPermissions("purchase:demand:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改Demand
     */
    @RequiresPermissions("purchase:demand:edit")
    @GetMapping("/edit/{demandId}")
    public String edit(@PathVariable("demandId") Long demandId, Model model)
    {
        Demand demand = demandService.selectDemandById(demandId);
        model.addAttribute("demand", demand);
        return prefix + "/edit";
    }

    /**
     * 保存Demand
     */
    @Log(title = "Purchase Management", action = "Demand Management - save Demand")
    @RequiresPermissions("purchase:demand:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Demand demand)
    {
        if (demandService.saveDemand(demand) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}
