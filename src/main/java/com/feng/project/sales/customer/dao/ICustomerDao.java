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
     * get Own customer
     * @return Customer list
     */

    public List<Customer> selectOwnCustomer(String loginName);
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

    /**
     * check customer Name Unique
     * @param customerName customer Name
     * @return customer number
     */
    public int checkNameUnique(String customerName);
}
