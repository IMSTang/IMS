package com.feng.project.sales.customer.controller;

import java.sql.SQLOutput;
import java.util.List;

import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.sales.customer.service.ICustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;



/**
 * customer info
 *
 * @author gzf
 */
@Controller
@RequestMapping("/sales/customer")
public class customerController extends BaseController{
    private String prefix = "/sales/customer";


    @Autowired
    private ICustomerService customerService;

    /**
     * return customer package
     * @return
     */
    @RequiresPermissions("sales:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }


    /**
     * get customer Info
     * @return customer list
     */
    @RequiresPermissions("sales:customer:view")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        List<Customer> customerList = customerService.selectAllCustomer();
        return getDataTable(customerList);
    }


    /**
     * delete customer by id(in fact update customer status 0 to 1)
     * @param customerId customer Id
     * @return json
     */
    @Log(title = "JHD", action = "remove customer")
    @RequiresPermissions("sales:customer:batchRemove")
    @GetMapping("/remove/{customerId}")
    @ResponseBody
    public JSON remove(@PathVariable("customerId") int customerId){
        if (customerService.deleteCustomerById(customerId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }


    /**
     * return add package
     * @param model
     * @return
     */
    @Log(title = "JHD", action = "add customer")
    @RequiresPermissions("sales:customer:add")
    @GetMapping("/add")
    public  String  add(Model model){
//        List<Customer> customers = customerService.selectAllCustomer();
//        model.addAttribute("customers",customers);

        return prefix +"/add";
    }


    /**
     * check customer Name Unique
     * @param customer
     * @return 1 exit,0 not exit
     */
    @PostMapping("/checkNameUnique")
    @ResponseBody
    public String  checkNameUnique(Customer customer){
        String flag="0";
          if(customer != null){
            flag = customerService.checkNameUnique(customer.getCustomerName());
        }
        return  flag;

    }

    @RequiresPermissions("sales:customer:save")
    @Log(title = "JHD", action = "save customer")
    @PostMapping("/save")
    @ResponseBody
    public JSON  save(Customer customer){
       if(customerService.saveCustomer(customer)>0) {
           return JSON.ok();
       }
       return JSON.error();
    }
}
