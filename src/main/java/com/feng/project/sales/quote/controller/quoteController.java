package com.feng.project.sales.quote.controller;


import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.sales.quote.domain.Quote;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.feng.project.sales.quote.service.IQuoteService;

import java.util.List;

@Controller
@RequestMapping("/sales/quote")
public class quoteController extends BaseController {
    private String prefix = "/sales/quote";

    @Autowired
    private IQuoteService quoteService;
    @RequiresPermissions("sales:quote:view")
    @GetMapping()
    public  String  quota(){
        return prefix +"/quote";
    }


    /**
     * get quote list
     * @param quote
     * @return
     */
    @RequiresPermissions("sales:quote:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Quote quote)
    {
        setPageInfo(quote);
        List<Quote> list = quoteService.selectQuoteList(quote);
        return getDataTable(list);
    }


    /**
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sales:quote:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        String date_today = DateUtils.dateTimeStr().substring(0,10);
        model.addAttribute("today", date_today);
        return prefix + "/add";
    }


    @Log(title = "sale  Management", action = "Quote Management - save Quote")
 //   @RequiresPermissions("sales:quote:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Quote quote)
    {
//        if (inquiryService.saveInquiry(inquiry) > 0)
//        {
//            return JSON.ok();
//        }
//        return JSON.error();
    System.out.println("ok****************");
    return JSON.ok();
    }

}
