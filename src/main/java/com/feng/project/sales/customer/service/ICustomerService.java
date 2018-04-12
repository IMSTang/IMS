package com.feng.project.sales.customer.service;
import java.util.List;
import com.feng.project.sales.customer.domain.Customer;

public interface ICustomerService {

    public List<Customer> selectAllCustomer();
    public int deleteCustomerById(int customerId);
}
