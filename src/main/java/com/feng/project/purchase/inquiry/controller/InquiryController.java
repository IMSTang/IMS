package com.feng.project.purchase.inquiry.controller;

import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.purchase.inquiry.domain.Inquiry;
import com.feng.project.purchase.inquiry.service.IInquiryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Inquiry信息操作处理
 * 
 * @author feng
 */
@Controller
@RequestMapping("/purchase/inquiry")
public class InquiryController extends BaseController
{
    private String prefix = "purchase/inquiry";

    @Autowired
    private IInquiryService inquiryService;

    @RequiresPermissions("purchase:inquiry:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/inquiry";
    }

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
     * 删除
     */
    @Log(title = "Purchase Management", action = "Inquiry Management - remove Inquiry")
    @RequiresPermissions("purchase:inquiry:remove")
    @RequestMapping("/remove/{inquiryId}")
    @ResponseBody
    public JSON remove(@PathVariable("inquiryId") Long inquiryId)
    {
        Inquiry inquiry = inquiryService.selectInquiryById(inquiryId);
        if (inquiry == null)
        {
            return JSON.error("Inquiry no exist");
        }
        if (inquiryService.deleteInquiryById(inquiryId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("purchase:inquiry:batchRemove")
    @Log(title = "Purchase Management", action = "Inquiry Management - batch Remove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = inquiryService.batchDeleteInquiry(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 新增Inquiry
     */
    @RequiresPermissions("purchase:inquiry:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        String date_today = DateUtils.dateTimeStr().substring(0,10);
        model.addAttribute("today", date_today);
        return prefix + "/add";
    }

    /**
     * 修改Inquiry
     */
    @RequiresPermissions("purchase:inquiry:edit")
    @GetMapping("/edit/{inquiryId}")
    public String edit(@PathVariable("inquiryId") Long inquiryId, Model model)
    {
        Inquiry inquiry = inquiryService.selectInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return prefix + "/edit";
    }

    /**
     * 保存Inquiry
     */
    @Log(title = "Purchase Management", action = "Inquiry Management - save Inquiry")
    @RequiresPermissions("purchase:inquiry:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Inquiry inquiry)
    {
        if (inquiryService.saveInquiry(inquiry) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}
