package com.feng.project.sales.customer.service;

import com.feng.common.constant.CustomerConstants;
import com.feng.project.sales.customer.dao.ICustomerDao;
import com.feng.project.sales.customer.domain.Customer;
import com.feng.project.system.role.dao.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.feng.common.utils.security.ShiroUtils;
import  com.feng.project.system.user.dao.IUserRoleDao;
import org.springframework.stereotype.Service;
import java.util.List;


//@Repository("customerService")
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private IUserRoleDao userRoleDao;

    @Autowired
    private IRoleDao roleDao;

    private static  String ROLE_KEY;
    /**
     * get customer list
     * @return
     */
    @Override
    public List<Customer> selectAllCustomer() {

        String loginName=ShiroUtils.getLoginName();
    //    equal current user prmission, get all info if is admin
        if (ROLE_KEY.equals(CustomerConstants.ADMINISTRATOR) || ROLE_KEY.equals(CustomerConstants.SALESMANAGER) ){
            return customerDao.selectAllCustomer();

        }


        return  customerDao.selectOwnCustomer(loginName);


    }


    /**
     * delete Customer By Id
     * @param customerId
     * @return
     */
    @Override
    public int deleteCustomerById(int customerId) {
        return customerDao.deleteCustomerById(customerId);
    }


    /**
     * save customer
     * @param customer
     * @return
     */
    @Override
    public int saveCustomer(Customer customer) {

   // int count = 0;
   // int customerId = customer.getCustomerId();
   customer.setCreateBy(ShiroUtils.getLoginName());
   customer.setRemark(ShiroUtils.getLoginName());
   return customerDao.insertCustomer(customer);


    }

    @Override
    public String checkNameUnique(String customerName) {
       int count = customerDao.checkNameUnique(customerName);
       if(count>0){
           return CustomerConstants.NAME_NOT_UNIQUE;
       }
       return  CustomerConstants.NAME_UNIQUE;
    }


    /**
     * batch Delete Customer
     * @param ids
     * @return
     */
    @Override
    public int batchDeleteCustomer(int[] ids) {
        return customerDao.batchDeleteCustomer(ids);
    }


    @Override
    public String initRole(){
        //get current login name
        String loginName=ShiroUtils.getLoginName();

        //get role id by user Id
        String userId = ShiroUtils.getUserId().toString();
        int roleId = userRoleDao.getRoleId(userId);
        Long lRoleId = new Long((long)roleId);

        //get role name
        ROLE_KEY  = (roleDao.selectRoleById(lRoleId)).getRoleKey();



        return ROLE_KEY;

    }
}
