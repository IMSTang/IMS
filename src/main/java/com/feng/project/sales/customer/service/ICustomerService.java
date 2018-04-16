package com.feng.project.sales.customer.service;
import java.util.List;
import com.feng.project.sales.customer.domain.Customer;

public interface ICustomerService {

    /**
     * select all customer info
     * @return customer list
     */
    public List<Customer> selectAllCustomer();

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
}
