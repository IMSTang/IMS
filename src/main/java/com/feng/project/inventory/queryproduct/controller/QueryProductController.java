package com.feng.project.inventory.queryproduct.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.queryproduct.domain.QueryProduct;
import com.feng.project.inventory.queryproduct.service.IQueryProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author feng
 */
@Controller
@RequestMapping("/inventory/queryproduct")
public class QueryProductController extends BaseController
{
    private String prefix = "inventory/queryproduct";

    @Autowired
    private IQueryProductService queryProductService;

    @RequiresPermissions("inventory:queryproduct:view")
    @GetMapping()
    public String queryProduct()
    {
        return prefix + "/queryproduct";
    }

    @RequiresPermissions("inventory:queryproduct:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(QueryProduct queryProduct)
    {
        setPageInfo(queryProduct);
        List<QueryProduct> list = queryProductService.selectQueryProductList(queryProduct);
        return getDataTable(list);
    }


    @RequiresPermissions("inventory:queryproduct:detail")
    @GetMapping("/detail/{sn}")
    public String detail(@PathVariable("sn") Long sn, Model model)
    {
        QueryProduct queryProduct = queryProductService.selectQueryProductById(sn);
        model.addAttribute("queryProduct", queryProduct);
        return prefix + "/detail";
    }

    @RequiresPermissions("inventory:queryproduct:batchDemand")
    @Log(title = "Inventory Management", action = "Query Product - batch Demand")
    @PostMapping("/batchDemand")
    @ResponseBody
    public JSON batchDemand(@RequestParam("arrayItemCode[]") String[] arrayItemCode)
    {
        int rows = queryProductService.batchDemand(arrayItemCode);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
}
