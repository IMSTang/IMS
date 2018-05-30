package com.feng.project.inventory.instock.controller;

import com.alibaba.fastjson.JSONObject;
import com.feng.common.utils.UploadFileUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.inventory.instock.service.IStockInService;
import com.feng.project.system.attach.service.IAttachmentService;
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
import com.feng.framework.web.domain.JSON;
import com.feng.project.system.attach.domain.Attachment;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

        @Autowired
        private IDictDataService dictDataService;

        @Autowired
        private IDictTypeService dictTypeService;

        @Autowired
        private IAttachmentService attachmentService;


        @RequiresPermissions("inventory:inStock:list")
        @GetMapping("/list")
        @ResponseBody
        public TableDataInfo list(StockIn stockIn)
        {
            setPageInfo(stockIn);
            List<StockIn> stockInList = stockInService.selectStockInList(stockIn);
            return getDataTable(stockInList);
        }

        @RequiresPermissions("inventory:inStock:detail")
        @GetMapping("/detail/{sn}")
        public String detail(@PathVariable("sn") Long sn, Model model)
        {
            StockIn stockIn = stockInService.selectStockInById(sn);
            model.addAttribute("stockIn", stockIn);

            List<Attachment> aList = attachmentService.selectAttachmentList("INV_IN", sn.intValue());

            model.addAttribute("attachmentList", aList);
            return prefix + "/detail";
        }

        @Log(title = "Inventory Management", action = "Inventory - inStock")
        @RequiresPermissions("inventory:inStock:add")
        @GetMapping("/add")
        public  String  add(Model model){
            DictType dictType = dictTypeService.selectDictTypeByType("Warehouse");
            List<DictData> dictDataDegree = dictDataService.selectDictDataByTypeId(dictType.getDictId());
            List<SelectedDictData> listWarehouse = new ArrayList<SelectedDictData>();
            for(DictData cate: dictDataDegree)
            {
                SelectedDictData data1 = new SelectedDictData();
                data1.setDictLabel(cate.getDictLabel());
                data1.setDictValue(cate.getDictValue());
                //add
                listWarehouse.add(data1);
            }
            model.addAttribute("listWarehouse", listWarehouse);

            return prefix +"/add";
        }



        @RequiresPermissions("inventory:inStock:save")
        @Log(title = "Inventory Management", action = "Inventory - save inStock")
        @PostMapping("/save")
        @ResponseBody
        public JSON  save(MultipartHttpServletRequest file, @RequestParam("StockIn") String stockIn){


            JSONObject jsStr = JSONObject.parseObject(stockIn);
            StockIn SI =  (StockIn)JSONObject.toJavaObject(jsStr,StockIn.class);
           Map<String,String> names=UploadFileUtils.saveFile(file);
            List<Attachment> attachments = new ArrayList<>();
            if(names != null) {

                for (Map.Entry<String, String> entry : names.entrySet()) {
                    attachments.add(new Attachment(entry.getValue(), entry.getKey()));
                }
            }
            SI.setAttachmentList(attachments);
            stockInService.spStockIn(SI);
            return JSON.ok();
        }



        @Log(title = "Inventory Management", action = "Inventory - remove inStock")
        @RequiresPermissions("inventory:inStock:remove")
        @RequestMapping("/remove/{id}")
        @ResponseBody
        public JSON remove(@PathVariable("id") int id){

            if(stockInService.spStockInRemove(id).equals("1")){
                return JSON.ok();
            }else{
                return JSON.error("This batch has been output, cannot remove.");
            }

        }



    }
