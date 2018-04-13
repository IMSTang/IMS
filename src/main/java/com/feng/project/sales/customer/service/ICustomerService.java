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

}
