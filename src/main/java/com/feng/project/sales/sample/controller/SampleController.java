package com.feng.project.sales.sample.controller;


import com.feng.common.utils.DateUtils;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.sales.customer.dao.ICustomerDao;
import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.sales.sample.domain.Sample;
import com.feng.project.sales.sample.domain.SampleBody;
import com.feng.project.sales.sample.service.ISampleBodyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.feng.project.sales.sample.service.ISampleService;

import java.util.List;

@Controller
@RequestMapping("/sales/sample")
public class SampleController extends BaseController {
    private String prefix = "/sales/sample";

    @Autowired
    private ISampleService sampleService;

    @Autowired
    private ISampleBodyService sampleBodyService;

//    @Autowired
//    private ICustomerDao customerDao;


    @RequiresPermissions("sales:sample:view")
    @GetMapping()
    public  String  sample(){
        String result = sampleService.initRole();
        if(result == null)
            return "error";

        return prefix +"/sample";
    }


    /**
     * get sample list
     * @param sample
     * @return
     */
    @RequiresPermissions("sales:sample:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Sample sample)
    {

        setPageInfo(sample);
        List<Sample> list = sampleService.selectSampleList(sample);
        return getDataTable(list);
    }


    /**
     *
     * @param model
     * @return
     */
    @RequiresPermissions("sales:sample:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        String date_today = DateUtils.dateTimeStr().substring(0,10);
        model.addAttribute("today", date_today);
        return prefix + "/add";

    }


    @Log(title = "sale  Management", action = "Sample Management - save Sample")
    @RequiresPermissions("sales:sample:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Sample sample)
    {

        int  rows =  sampleService.insertSample(sample);
         if( rows>0){
                return JSON.ok();
         }
        if(rows<0){
            return  JSON.error("Item code not exist!");
        }
        return  JSON.error();
    }


    @Log(title = "Sales Management", action = "Sample - remove Sample")
    @RequiresPermissions("sales:sample:remove")
    @RequestMapping("/remove/{sampleBodyId}/{sampleId}")
    @ResponseBody
    public JSON remove(@PathVariable("sampleBodyId") Long sampleBodyId,@PathVariable("sampleId") Long sampleId ){


        if (  sampleBodyService.deleteSampleBodyById(sampleBodyId, sampleId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }


    @RequiresPermissions("sales:sample:batchRemove")
    @Log(title = "Sales Management", action = "Sample - batchRemoveSample")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("sampleBodyId[]")Long[] sampleBodyId,@RequestParam("sampleId[]")Long[] sampleId){
        int rows = sampleBodyService.batchDeleteSample(sampleBodyId,sampleId);
        if(rows>0){

            return  JSON.ok();
        }

        return  JSON.error();
    }

    @Log(title = "Sales Management", action = "Sample - edit")
    @RequiresPermissions("sales:sample:edit")
    @GetMapping("/edit/{sampleId}")
    public String edit(@PathVariable("sampleId") Long sampleId, Model model)
    {
            Sample sample = sampleService.selectSampleById(sampleId);
            List<SampleBody> sampleBodies= sampleBodyService.selectBodyBySampleId(sampleId);
            for (int i=0;i<sampleBodies.size();i++)
            model.addAttribute("sampleBodies", sampleBodies);
            model.addAttribute("sample", sample);
        return prefix + "/edit";
    }



}
