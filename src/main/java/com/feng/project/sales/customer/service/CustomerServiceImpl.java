package com.feng.project.sales.customer.service;

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
}
