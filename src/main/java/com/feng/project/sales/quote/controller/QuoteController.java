package com.feng.project.sales.quote.controller;


import com.feng.common.utils.DateUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.sales.customer.dao.ICustomerDao;
import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.sales.quote.domain.Quote;
import com.feng.project.sales.quote.domain.QuoteBody;
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
public class QuoteController extends BaseController {
    private String prefix = "/sales/quote";

    @Autowired
    private IQuoteService quoteService;

    @Autowired
    private IQuoteBodyService quoteBodyService;

//    @Autowired
//    private ICustomerDao customerDao;


    @RequiresPermissions("sales:quote:view")
    @GetMapping()
    public  String  quote(){
        if(ShiroUtils.ROLE_KEY == null) {
            String result = quoteService.initRole();
            if (result == null)
                return "error";
        }
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
        int  rows =  quoteService.insertQuote(quote);
         if( rows>0){
                return JSON.ok();
         }
        if(rows<0){
            return  JSON.error("Item code not exist!");
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

    @Log(title = "Sales Management", action = "Quote - edit")
    @RequiresPermissions("sales:quote:edit")
    @GetMapping("/edit/{quoteId}")
    public String edit(@PathVariable("quoteId") Long quoteId, Model model)
    {
            Quote quote = quoteService.selectQuoteById(quoteId);
            List<QuoteBody> quoteBodies= quoteBodyService.selectBodyByQuoteId(quoteId);
            for (int i=0;i<quoteBodies.size();i++)
            model.addAttribute("quoteBodies", quoteBodies);
            model.addAttribute("quote", quote);
        return prefix + "/edit";
    }



}
