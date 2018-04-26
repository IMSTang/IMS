package com.feng.project.purchase.inquiry.controller;


import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.purchase.vendor.dao.IVendorDao;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.purchase.inquiry.domain.Inquiry;
import com.feng.project.purchase.inquiry.domain.InquiryBody;
import com.feng.project.purchase.inquiry.service.IInquiryBodyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.project.purchase.inquiry.service.IInquiryService;

import java.util.List;

@Controller
@RequestMapping("/purchase/inquiry")
public class InquiryController extends BaseController {
    private String prefix = "/purchase/inquiry";

    @Autowired
    private IInquiryService inquiryService;

    @Autowired
    private IInquiryBodyService inquiryBodyService;

//    @Autowired
//    private IVendorDao vendorDao;


    @RequiresPermissions("purchase:inquiry:view")
    @GetMapping()
    public  String  inquiry(){
        return prefix +"/inquiry";
    }


    /**
     * get inquiry list
     * @param inquiry
     * @return
     */
    @RequiresPermissions("purchase:inquiry:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Inquiry inquiry)
    {

        setPageInfo(inquiry);
        List<Inquiry> list = inquiryService.selectInquiryList(inquiry);
        return getDataTable(list);
    }


    /**
     *
     * @param model
     * @return
     */
    @RequiresPermissions("purchase:inquiry:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        String date_today = DateUtils.dateTimeStr().substring(0,10);
        model.addAttribute("today", date_today);
        return prefix + "/add";

    }


    @Log(title = "sale  Management", action = "Inquiry Management - save Inquiry")
    @RequiresPermissions("purchase:inquiry:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Inquiry inquiry)
    {

        int  rows =  inquiryService.insertInquiry(inquiry);
         if( rows>0){
                return JSON.ok();
         }
        if(rows<0){
            return  JSON.error("Item code not exist!");
        }
        return  JSON.error();
    }


    @Log(title = "Purchase Management", action = "Inquiry - remove Inquiry")
    @RequiresPermissions("purchase:inquiry:remove")
    @RequestMapping("/remove/{inquiryBodyId}/{inquiryId}")
    @ResponseBody
    public JSON remove(@PathVariable("inquiryBodyId") Long inquiryBodyId,@PathVariable("inquiryId") Long inquiryId ){


        if (  inquiryBodyService.deleteInquiryBodyById(inquiryBodyId, inquiryId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }


    @RequiresPermissions("purchase:inquiry:batchRemove")
    @Log(title = "Purchase Management", action = "Inquiry - batchRemoveInquiry")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("inquiryBodyId[]")Long[] inquiryBodyId,@RequestParam("inquiryId[]")Long[] inquiryId){
        int rows = inquiryBodyService.batchDeleteInquiry(inquiryBodyId,inquiryId);
        if(rows>0){

            return  JSON.ok();
        }

        return  JSON.error();
    }

    @Log(title = "Purchase Management", action = "Inquiry - edit")
    @RequiresPermissions("purchase:inquiry:edit")
    @GetMapping("/edit/{inquiryId}")
    public String edit(@PathVariable("inquiryId") Long inquiryId, Model model)
    {
            Inquiry inquiry = inquiryService.selectInquiryById(inquiryId);
            List<InquiryBody> inquiryBodies= inquiryBodyService.selectBodyByInquiryId(inquiryId);
            for (int i=0;i<inquiryBodies.size();i++)
            model.addAttribute("inquiryBodies", inquiryBodies);
            model.addAttribute("inquiry", inquiry);
        return prefix + "/edit";
    }



}
