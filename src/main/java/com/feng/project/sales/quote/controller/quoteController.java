package com.feng.project.sales.quote.controller;


import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.sales.quote.domain.Quote;
import com.feng.project.sales.quote.service.IQuoteBodyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.project.sales.quote.service.IQuoteService;

import java.util.List;

@Controller
@RequestMapping("/sales/quote")
public class quoteController extends BaseController {
    private String prefix = "/sales/quote";

    @Autowired
    private IQuoteService quoteService;

    @Autowired
    private IQuoteBodyService quoteBodyService;




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
    @RequiresPermissions("sales:quote:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Quote quote)
    {
         if(  quoteService.insertQuote(quote)>0){
                return JSON.ok();
         }
        return  JSON.error();
    }


    @Log(title = "Sales Management", action = "Quote - remove Quote")
    @RequiresPermissions("sales:quote:remove")
    @RequestMapping("/remove/{quoteBodyId}/{quoteId}")
    @ResponseBody
    public JSON remove(@PathVariable("quoteBodyId") Long quoteBodyId,@PathVariable("quoteId") Long quoteId ){


        if (  quoteBodyService.deleteQuoteBodyById(quoteBodyId, quoteId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }


    @RequiresPermissions("sales:quote:batchRemove")
    @Log(title = "Sales Management", action = "Quote - batchRemoveQuote")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("quoteBodyId[]")Long[] quoteBodyId,@RequestParam("quoteId[]")Long[] quoteId){
        int rows = quoteBodyService.batchDeleteQuote(quoteBodyId,quoteId);
        if(rows>0){

            return  JSON.ok();
        }
        return  JSON.error();
    }


}
