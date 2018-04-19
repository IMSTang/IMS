package com.feng.project.product.productionMapping.controller;


import java.util.List;

import com.feng.project.product.productionMapping.domain.ProductionMapping;
import com.feng.project.product.productionMapping.service.IProductionMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;


@Controller
@RequestMapping("/product/productionMapping")
public class ProductionMappingController extends BaseController{
    private String prefix = "/product/productionMapping";


    @RequiresPermissions("product:productionMapping:view")
    @GetMapping()
    public String production()

    {
        return prefix + "/productionMapping";
    }
    @Autowired
    private IProductionMappingService productionMappingService;

    @RequiresPermissions("product:productionMapping:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductionMapping productionMapping)
    {
        setPageInfo(productionMapping);
        List<ProductionMapping> productionMappingList = productionMappingService.selectProductionMappingList(productionMapping);
        return getDataTable(productionMappingList);
    }


    @RequiresPermissions("product:productionMapping:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }


    @RequiresPermissions("product:productionMapping:save")
    @Log(title = "Productions Management", action = "Production - save productionMapping")
    @PostMapping("/save")
    @ResponseBody
    public JSON  save(ProductionMapping productionMapping){
        if(productionMappingService.saveProductionMapping(productionMapping)>0) {
            return JSON.ok();
        }
        return JSON.error();
    }



    @Log(title = "Productions Management", action = "ProductionMapping - edit productionMapping")
    @RequiresPermissions("product:productionMapping:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        ProductionMapping productionMapping = productionMappingService.selectProductionMappingById(id);
        model.addAttribute("productionMapping", productionMapping);
        return prefix + "/edit";
    }


    @RequiresPermissions("product:productionMapping:batchRemove")
    @Log(title = "Productions Management", action = "Production - batch delete productionMapping")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("ids[]")Long[] ids){
        int rows = productionMappingService.batchDeleteProductionMapping(ids);
        if(rows>0){
            return  JSON.ok();
        }
        return  JSON.error();
    }


    @Log(title = "Productions Management", action = "Production - remove productionMapping")
    @RequiresPermissions("product:productionMapping:remove")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id){
        if (productionMappingService.deleteProductionMappingById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }
}
