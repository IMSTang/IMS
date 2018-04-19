package com.feng.project.sales.customer.service;
import java.util.List;
import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.sales.customer.domain.CustomerIdName;

public interface ICustomerService {

    /**
     * select all customer info
     * @return customer list
     */
    public List<Customer> selectAllCustomer(Customer customer);

    /**
     * delete customer by id
     * @param customerId
     * @return
     */
    public int deleteCustomerById(int customerId);


    public  int saveCustomer(Customer customer);

    /**
     * check customer Name Unique
     * @param customerName customer Name
     * @return customer number
     */
    public  String checkNameUnique(String  customerName);


    /**
     * batch Delete Customer
     * @param ids
     * @return
     */
    public  int batchDeleteCustomer(int[] ids);


    public String initRole();




    /**
     * get customer by id
     * @param customerId id
     * @return Customer object
     */
    public Customer selectCustomerById(int customerId);




    /**
     * get customer id and name
     * @param customer
     * @return
     */
    public List<CustomerIdName> selectCustomerIdName(Customer customer);

}
