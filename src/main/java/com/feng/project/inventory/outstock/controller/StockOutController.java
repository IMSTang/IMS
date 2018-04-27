package com.feng.project.inventory.outstock.controller;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.outstock.domain.Inventory;
import com.feng.project.inventory.outstock.domain.StockOut;
import com.feng.project.inventory.outstock.service.IStockOutService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.framework.web.domain.JSON;

import java.util.List;


    @Controller
    @RequestMapping("/inventory/outStock")
    public class StockOutController extends BaseController {
        private String prefix = "/inventory/outStock";


        @RequiresPermissions("inventory:outStock:view")
        @GetMapping()
        public String production()

        {
          return prefix + "/outStock";
        }
        @Autowired
        private IStockOutService stockOutService;


        @RequiresPermissions("inventory:outStock:list")
        @GetMapping("/list")
        @ResponseBody
        public TableDataInfo list(StockOut stockOut)
        {
            setPageInfo(stockOut);
            List<StockOut> stockOutList = stockOutService.selectStockOutList(stockOut);
            return getDataTable(stockOutList);
        }

        @Log(title = "Inventory Management", action = "Inventory - outStock")
        @RequiresPermissions("inventory:outStock:add")
        @GetMapping("/add")
        public  String  add(Model model){
            return prefix +"/add";
        }

        @RequiresPermissions("inventory:outStock:detail")
        @GetMapping("/detail/{sn}")
        public String detail(@PathVariable("sn") Long sn, Model model)
        {
            StockOut stockOut = stockOutService.selectStockOutById(sn);
            model.addAttribute("stockOut", stockOut);
            return prefix + "/detail";
        }

        @RequiresPermissions("inventory:outStock:save")
        @Log(title = "Inventory Management", action = "Inventory - save outStock")
        @PostMapping("/save")
        @ResponseBody
        public JSON  save(Inventory inventory){

            System.out.println("--------------save----------");
            System.out.println("--------------save--- inventory.getQuantityStockOut()-------"+inventory.getQuantityStockOut());

            StockOut stockOut =new StockOut();
            stockOut.setItemCode(inventory.getItemCode());
            stockOut.setBatch(inventory.getBatch());
            stockOut.setWarehouse(inventory.getWarehouse());
            stockOut.setPosition(inventory.getPosition());
            stockOut.setCustomerId(inventory.getCustomerId());   // this is user may input;
            stockOut.setIrradiation(inventory.getIrradiation());
            stockOut.setPriceFobOntario(inventory.getPriceFobOntario());
            stockOut.setPricePurchase(inventory.getPricePurchase());
            stockOut.setQuantity(inventory.getQuantityStockOut());  /// this is the user input stockout quantity;
            stockOut.setTpc(inventory.getTpc());
            stockOut.setVendorId(inventory.getVendorId());
            stockOut.setSn(inventory.getSn());  // this is the sn from inventory table. use for the sp to generate stockout.

                 int qInv=Integer.parseInt(inventory.getQuantity());
                 int qOut=Integer.parseInt(inventory.getQuantityStockOut());
            if(qOut>qInv) {
                return JSON.error("StockOut Quantity should no more than Inventory Quantity!");
            }
            stockOutService.spStockOut(stockOut);
                return JSON.ok();
        }



        @Log(title = "Inventory Management", action = "Inventory - remove outStock")
        @RequiresPermissions("inventory:outStock:remove")
        @RequestMapping("/remove/{id}")
        @ResponseBody
        public JSON remove(@PathVariable("id") int id){
            stockOutService.spStockOutRemove(id);
                return JSON.ok();
        }


        @Log(title = "Inventory Management", action = "Inventory - stock out inStock")
        @RequiresPermissions("inventory:outStock:edit")
        @GetMapping("/edit/{id}")
        public String edit(@PathVariable("id") int id, Model model)
        {
            System.out.println("-----------------EDIT --------------"+id);

            Inventory inventory = stockOutService.selectInventoryByInStockId(id);
            System.out.println("inventory  SN    -----------"+inventory.getSn());

            model.addAttribute("inventory", inventory);

            return prefix + "/edit";
        }

    }
