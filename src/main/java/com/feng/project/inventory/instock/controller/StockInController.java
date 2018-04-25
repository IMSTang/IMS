package com.feng.project.inventory.instock.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.inventory.instock.service.IStockInService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;

import java.util.List;


    @Controller
    @RequestMapping("/inventory/inStock")
    public class StockInController extends BaseController {
        private String prefix = "/inventory/inStock";


        @RequiresPermissions("inventory:inStock:view")
        @GetMapping()
        public String production()

        {
          return prefix + "/inStock";
        }
        @Autowired
        private IStockInService stockInService;


        @RequiresPermissions("inventory:inStock:list")
        @GetMapping("/list")
        @ResponseBody
        public TableDataInfo list(StockIn stockIn)
        {
            setPageInfo(stockIn);
            List<StockIn> stockInList = stockInService.selectStockInList(stockIn);
            return getDataTable(stockInList);
        }


        @Log(title = "Inventory Management", action = "Inventory - inStock")
        @RequiresPermissions("inventory:inStock:add")
        @GetMapping("/add")
        public  String  add(Model model){
            return prefix +"/add";
        }



        @RequiresPermissions("inventory:inStock:save")
        @Log(title = "Inventory Management", action = "Inventory - save inStock")
        @PostMapping("/save")
        @ResponseBody
        public JSON  save(StockIn stockIn){
            System.out.println("---------save-----------------");
            System.out.println("---------save---"+stockIn.getBatch()+"-----");
            stockInService.spStockIn(stockIn);
                return JSON.ok();
        }



        @Log(title = "Inventory Management", action = "Inventory - remove inStock")
        @RequiresPermissions("inventory:inStock:remove")
        @RequestMapping("/remove/{id}")
        @ResponseBody
        public JSON remove(@PathVariable("id") int id){
            stockInService.spStockInRemove(id);
                return JSON.ok();
        }



    }
