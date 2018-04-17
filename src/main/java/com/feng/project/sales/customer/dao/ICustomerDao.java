package com.feng.project.sales.customer.dao;


import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.feng.project.sales.customer.domain.Customer;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ICustomerDao {

    /**
     * get all customer
     * @return Customer list
     */
    public List<Customer> selectAllCustomer(Customer customer);


    /**
     * get Own customer
     * @return Customer list
     */

    public List<Customer> selectOwnCustomer(@Param("loginName") String loginName, @Param("searchValue") String searchValue);
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


    /**
     * batch Delete Customer
     * @param ids
     * @return
     */
    public  int batchDeleteCustomer(int[] ids);


    /**
     * get customer by id
     * @param customerId id
     * @return Customer object
     */
    public Customer selectCustomerById(int customerId);

    /**
     * update customer
     * @param customer
     * @return
     */
    public  int  updateCustomer(Customer customer);

}
