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

    /**
     * delete customer by id
     * @param customerId
     * @return
     */
    public int deleteCustomerById(int customerId);

    /**
     * insert customer
     * @param customer
     * @return
     */
    public int insertCustomer(Customer customer);
}
