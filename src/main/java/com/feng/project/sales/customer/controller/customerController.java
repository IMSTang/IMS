package com.feng.project.sales.customer.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.system.post.domain.Post;
import com.feng.project.system.post.service.IPostService;


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
        System.out.println("------------------------------------查询数据库之前- ---------------------------" );
        List<Customer> customerList = customerService.selectAllCustomer();

        System.out.println("-------------------------------------1---------------------------"+((Customer)customerList.get(0)).getCustomerName());

        return getDataTable(customerList);
    }

    @Log(title = "JHD", action = "remove customer")
    @RequiresPermissions("sales:customer:remove")
    @GetMapping("/remove/{customerId}")
    @ResponseBody
    public JSON remove(@PathVariable("customerId") int customerId){
        if (customerService.deleteCustomerById(customerId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();

    }

}
