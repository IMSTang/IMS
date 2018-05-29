package com.feng.project.inventory.queryinventory.controller;

import com.feng.common.utils.DateUtils;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.queryinventory.domain.QueryInventory;
import com.feng.project.inventory.queryinventory.service.IQueryInventoryService;
import com.feng.project.purchase.inquiry.service.IInquiryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    private IInquiryService inquiryService;

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

        if(itemCode ==null || itemCode==""){
            return new ArrayList<QueryInventory>();
        }
        List<QueryInventory> list = queryInventoryService.selectQueryInventoryListByBatch(batch,itemCode);

//        System.out.println(list);
        return list;
    }

    @GetMapping("/search_itemcode")
    @ResponseBody
    public Map search_itemcode(HttpServletRequest request, HttpServletResponse response)
    {
        Map itemInfo = new HashMap();

        String itemCode = request.getParameter("itemCode");
        if(itemCode ==null || itemCode==""){
            return itemInfo;
        }
        String min_max_price = inquiryService.selectMinMaxPriceByItemCode(itemCode);
        if(min_max_price == null || min_max_price == "") {
            min_max_price = "no inquiry price in 3 months.";
        }else{
            min_max_price = "US $ " + min_max_price;
        }
        itemInfo.put("vendorRefPrice", min_max_price);

        itemInfo.put("recentQuotaPrice","28 - 38");
        List<QueryInventory> list = queryInventoryService.selectQueryInventoryListByItemCode(itemCode);
        itemInfo.put("inventoryList",list);

        String date_today = DateUtils.dateTimeStr().substring(0,10);
        itemInfo.put("Today",date_today);
        return itemInfo;
    }

    @RequiresPermissions("inventory:queryinventory:detail")
    @GetMapping("/detail/{sn}")
    public String detail(@PathVariable("sn") Long sn, Model model)
    {
        QueryInventory queryInventory = queryInventoryService.selectQueryInventoryById(sn);
        model.addAttribute("queryInventory", queryInventory);

        List<QueryInventory> detailList = queryInventoryService.selectQueryInventoryListEqualBatch(queryInventory.getBatch(),queryInventory.getItemCode());
        model.addAttribute("detailList", detailList);
        return prefix + "/detail";
    }

    /**
     * check ItemCode + Batch Unique
     * @param inventory
     * @return 1 exit,0 not exit
     */
    @PostMapping("/checkItemBatchUnique")
    @ResponseBody
    public String  checkItemBatchUnique(QueryInventory inventory){
        String flag="0";

        if(inventory != null){
            flag = queryInventoryService.checkItemBatchUnique(inventory.getItemCode(),inventory.getBatch());
        }
        return  flag;

    }
}
