package com.feng.project.inventory.salesreturn.controller;

import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.salesreturn.domain.SalesReturn;
import com.feng.project.inventory.salesreturn.service.ISalesReturnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.framework.web.domain.JSON;

import java.util.List;


    @Controller
    @RequestMapping("/inventory/salesReturn")
    public class SalesReturnController extends BaseController {
        private String prefix = "/inventory/salesReturn";


        @RequiresPermissions("inventory:salesReturn:view")
        @GetMapping()
        public String production()

        {
          return prefix + "/salesReturn";
        }
        @Autowired
        private ISalesReturnService salesReturnService;


        @RequiresPermissions("inventory:salesReturn:list")
        @GetMapping("/list")
        @ResponseBody
        public TableDataInfo list(SalesReturn salesReturn)
        {
            setPageInfo(salesReturn);
            List<SalesReturn> salesReturnList = salesReturnService.selectSalesReturnList(salesReturn);
            return getDataTable(salesReturnList);
        }

        @Log(title = "Inventory Management", action = "Inventory - salesReturn")
        @RequiresPermissions("inventory:salesReturn:add")
        @GetMapping("/add")
        public  String  add(Model model){
            String date_today = DateUtils.dateTimeStr().substring(0,10);
            model.addAttribute("today", date_today);
            return prefix +"/add";
        }

        @RequiresPermissions("inventory:salesReturn:detail")
        @GetMapping("/detail/{sn}")
        public String detail(@PathVariable("sn") Long sn, Model model)
        {
            SalesReturn salesReturn = salesReturnService.selectSalesReturnById(sn);
            model.addAttribute("salesReturn", salesReturn);
            return prefix + "/detail";
        }

        @RequiresPermissions("inventory:salesReturn:save")
        @Log(title = "Inventory Management", action = "Inventory - save salesReturn")
        @PostMapping("/save")
        @ResponseBody
        public JSON  save(SalesReturn salesReturn){
            System.out.println("--------------save----------");
            salesReturnService.spSalesReturn(salesReturn);
                return JSON.ok();
        }



        @Log(title = "Inventory Management", action = "Inventory - remove salesReturn")
        @RequiresPermissions("inventory:salesReturn:remove")
        @RequestMapping("/remove/{id}")
        @ResponseBody
        public JSON remove(@PathVariable("id") int id){
            salesReturnService.spSalesReturnRemove(id);
                return JSON.ok();
        }



    }
