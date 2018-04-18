package com.feng.project.sales.quote.controller;


import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.sales.quote.domain.Quote;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


    @RequiresPermissions("sales:quote:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Quote quote)
    {
        setPageInfo(quote);
        List<Quote> list = quoteService.selectQuoteList(quote);
        return getDataTable(list);
    }

}
