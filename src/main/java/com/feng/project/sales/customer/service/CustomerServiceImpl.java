package com.feng.project.sales.customer.service;

import com.feng.common.constant.CustomerConstants;
import com.feng.project.sales.customer.dao.ICustomerDao;
import com.feng.project.sales.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.feng.common.utils.security.ShiroUtils;
import  com.feng.project.system.user.dao.IUserRoleDao;
import com.feng.project.system.user.service.UserServiceImpl;
import java.util.List;
@Repository("customerService")
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private IUserRoleDao userRoleDao;

    /**
     * get customer list
     * @return
     */
    @Override
    public List<Customer> selectAllCustomer() {
        //get current login name
        String loginName=ShiroUtils.getLoginName();

        //get role id by user Id
        String userId = ShiroUtils.getUserId().toString();
        int roleId = userRoleDao.getRoleId(userId);


        //equal current user prmission, get all info if is admin
        if (roleId == CustomerConstants.ADMINISTRATOR){
            return customerDao.selectAllCustomer();

        }

        return  customerDao.selectOwnCustomer(loginName);
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
