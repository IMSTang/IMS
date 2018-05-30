package com.feng.project.sales.customer.controller;


import java.util.List;

import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.sales.customer.domain.CustomerIdName;
import com.feng.project.sales.customer.service.ICustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(ShiroUtils.ROLE_KEY == null) {
            String result = customerService.initRole();
            if (result == null)
                return "error";
        }
        return prefix + "/customer";
    }







    /**
     * get customer Info
     * @return customer list
     */
    @RequiresPermissions("sales:customer:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Customer customer)
    {

        setPageInfo(customer);
        List<Customer> customerList = customerService.selectAllCustomer(customer);
        return getDataTable(customerList);
    }



    @GetMapping("/search_name")
    @ResponseBody
    public List<CustomerIdName> search_name(Customer customer)
    {


        List<CustomerIdName> customerList = customerService.selectCustomerIdName(customer);
        return customerList;

    }






    /**
     * delete customer by id(in fact update customer status 0 to 1)
     * @param customerId customer Id
     * @return json
     */
    @Log(title = "Sales Management", action = "Customer - remove customer")
    @RequiresPermissions("sales:customer:remove")
    @RequestMapping("/remove/{customerId}")
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
    @Log(title = "Sales Management", action = "Customer -add customer")
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
    @Log(title = "Sales Management", action = "Customer - save customer")
    @PostMapping("/save")
    @ResponseBody
    public JSON  save(Customer customer){
       if(customerService.saveCustomer(customer)>0) {
           return JSON.ok();
       }
       return JSON.error();
    }


    /**
     *batch Remove customer
     */
    @RequiresPermissions("sales:customer:batchRemove")
    @Log(title = "Sales Management", action = "Customer - batchRemoveCustomer")
    @PostMapping("/batchRemove")
    @ResponseBody
    public  JSON batchRemove(@RequestParam("ids[]")int[] ids){
        int rows = customerService.batchDeleteCustomer(ids);
        if(rows>0){
            return  JSON.ok();
        }
        return  JSON.error();
    }


    @Log(title = "Sales Management", action = "Customer - edit Customer")
    @RequiresPermissions("sales:customer:edit")
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") int customerId,Model model){
        Customer customer = customerService.selectCustomerById(customerId);
        model.addAttribute("customer",customer);
        return  prefix+"/edit";
    }


    @PostMapping("/checkNameExist")
    @ResponseBody
    public String  checkNameExist(String  name){
        String flag="0";
        if(name != null){
            flag = customerService.checkNameUnique(name);
        }
        return  flag;

    }

}
