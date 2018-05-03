package com.feng.project.inventory.queryinventory.controller;

import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.queryinventory.domain.QueryInventory;
import com.feng.project.inventory.queryinventory.service.IQueryInventoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 操作日志记录
 * 
 * @author feng
 */
@Controller
@RequestMapping("/inventory/queryinventory")
public class QueryInventoryController extends BaseController
{
    private String prefix = "inventory/queryinventory";

    @Autowired
    private IQueryInventoryService queryInventoryService;

    @RequiresPermissions("inventory:queryinventory:view")
    @GetMapping()
    public String queryinventory()
    {
        return prefix + "/queryinventory";
    }

    @RequiresPermissions("inventory:queryinventory:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(QueryInventory queryInventory)
    {
        setPageInfo(queryInventory);
        List<QueryInventory> list = queryInventoryService.selectQueryInventoryList(queryInventory);
        return getDataTable(list);
    }


    @GetMapping("/search_batch")
    @ResponseBody
    public List<QueryInventory> search_batch(HttpServletRequest request, HttpServletResponse response)
    {
        String batch = request.getParameter("batchValue");
        String itemCode = request.getParameter("itemCode");
        List<QueryInventory> list = queryInventoryService.selectQueryInventoryListByBatch(batch,itemCode);

        System.out.println(list);
        return list;
    }

    @RequiresPermissions("inventory:queryinventory:detail")
    @GetMapping("/detail/{sn}")
    public String detail(@PathVariable("sn") Long sn, Model model)
    {
        QueryInventory queryInventory = queryInventoryService.selectQueryInventoryById(sn);
        model.addAttribute("queryInventory", queryInventory);
        return prefix + "/detail";
    }
}
