package com.feng.project.product.production.controller;


import java.util.ArrayList;
import java.util.List;

import com.feng.project.product.production.domain.Production;
import com.feng.project.product.production.domain.ProductionSimple;
import com.feng.project.product.production.service.IProductionService;
import com.feng.project.system.dict.domain.DictData;
import com.feng.project.system.dict.domain.DictType;
import com.feng.project.system.dict.domain.SelectedDictData;
import com.feng.project.system.dict.service.IDictDataService;
import com.feng.project.system.dict.service.IDictTypeService;
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
@RequestMapping("/product/production")
public class ProductionController extends BaseController{
    private String prefix = "/product/production";


    @RequiresPermissions("product:production:view")
    @GetMapping()
    public String production()

    {
        return prefix + "/production";
    }
    @Autowired
    private IProductionService productionService;

    @Autowired
    private IDictDataService dictDataService;

    @Autowired
    private IDictTypeService dictTypeService;


    @RequiresPermissions("product:production:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Production production)
    {
        setPageInfo(production);
        List<Production> productionList = productionService.selectProductionList(production);
        return getDataTable(productionList);
    }

    @Log(title = "Productions Management", action = "Production - remove production")
    @RequiresPermissions("product:production:remove")
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public JSON remove(@PathVariable("id") Long id){
        if (productionService.deleteProductionById(id) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }

    @Log(title = "Productions Management", action = "Production - add production")
    @RequiresPermissions("product:production:add")
    @GetMapping("/add")
    public  String  add(Model model){

        DictType dictType = dictTypeService.selectDictTypeByType("ProductCategory");
        List<DictData> dictDataCategory = dictDataService.selectDictDataByTypeId(dictType.getDictId());
        List<SelectedDictData> listProductCategory = new ArrayList<SelectedDictData>();
        for(DictData cate: dictDataCategory)
        {
            SelectedDictData data1 = new SelectedDictData();
            data1.setDictLabel(cate.getDictLabel());
            data1.setDictValue(cate.getDictValue());
            listProductCategory.add(data1);
        }
        model.addAttribute("listProductCategory", listProductCategory);
        return prefix +"/add";
    }

    @RequiresPermissions("product:production:save")
    @Log(title = "Productions Management", action = "Production - save production")
    @PostMapping("/save")
    @ResponseBody
    public JSON  save(Production production){
       if(productionService.saveProduction(production)>0) {
           return JSON.ok();
       }
       return JSON.error();
    }

    @RequiresPermissions("product:production:batchRemove")
    @Log(title = "Productions Management", action = "Production - batch delete production")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("ids[]")Long[] ids){
        int rows = productionService.batchDeleteProduction(ids);
        if(rows>0){
            return  JSON.ok();
        }
        return  JSON.error();
    }



    @Log(title = "Productions Management", action = "Production - edit production")
    @RequiresPermissions("product:production:edit")
    @GetMapping("/edit/{productionId}")
    public String edit(@PathVariable("productionId") Long productionId, Model model)
    {
        Production production = productionService.selectProductionById(productionId);
        String productCategory = production.getProductCategory();

        DictType dictType = dictTypeService.selectDictTypeByType("ProductCategory");
        List<DictData> dictDataCategory = dictDataService.selectDictDataByTypeId(dictType.getDictId());
        List<SelectedDictData> listProductCategory = new ArrayList<SelectedDictData>();
        for(DictData cate: dictDataCategory)
        {
            SelectedDictData data1 = new SelectedDictData();
            data1.setDictLabel(cate.getDictLabel());
            data1.setDictValue(cate.getDictValue());

            if(data1.getDictLabel().equals(productCategory)){
                data1.setFlag(true);
            }else{
                data1.setFlag(false);
            }
            listProductCategory.add(data1);
        }
        model.addAttribute("listProductCategory", listProductCategory);
        model.addAttribute("production", production);
        return prefix + "/edit";
    }


    @GetMapping("/search/{searchStr}/{type}")
    @ResponseBody
    public List<ProductionSimple> search(@PathVariable("searchStr") String searchStr, @PathVariable("type") String type)
    {
        List<ProductionSimple> psList = productionService.selectProductionSimpleList(searchStr, type);
        return psList;
    }

    /**
     * check ItemCode Name Unique
     * @param production
     * @return 1 exit,0 not exit
     */
    @PostMapping("/checkItemCodeUnique")
    @ResponseBody
    public String  checkNameUnique(Production production){
        String flag="0";
        if(production != null){
            flag = productionService.checkItemCodeUnique(production.getItemCode());
        }
        return  flag;

    }

}
