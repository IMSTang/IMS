package com.feng.project.sales.customer.dao;


import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.feng.project.sales.customer.domain.Customer;
@Mapper
public interface ICustomerDao {

    /**
     * get all customer
     * @return Customer list
     */
    public List<Customer> selectAllCustomer();


    public int deleteCustomerById(int customerId);
}
