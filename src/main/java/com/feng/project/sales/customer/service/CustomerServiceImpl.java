package com.feng.project.sales.customer.service;

import com.feng.common.constant.CustomerConstants;
import com.feng.project.sales.customer.dao.ICustomerDao;
import com.feng.project.sales.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("customerService")
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerDao customerDao;

    @Override
    public List<Customer> selectAllCustomer() {
        return customerDao.selectAllCustomer();
    }

    @Override
    public int deleteCustomerById(int customerId) {
        return customerDao.deleteCustomerById(customerId);
    }

    @Override
    public int saveCustomer(Customer customer) {
        return 0;
    }

    @Override
    public String checkNameUnique(String customerName) {
       int count = customerDao.checkNameUnique(customerName);
       if(count>0){
           return CustomerConstants.NAME_NOT_UNIQUE;
       }
       return  CustomerConstants.NAME_UNIQUE;
    }
}
