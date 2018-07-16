package com.feng.project.inventory.salesreturn.service;
import com.feng.common.utils.StringUtils;
import com.feng.project.inventory.salesreturn.dao.ISalesReturnDao;
import com.feng.project.inventory.salesreturn.domain.SalesReturn;
import org.springframework.beans.factory.annotation.Autowired;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("salesReturnService")
public class SalesReturnServiceImpl implements ISalesReturnService {
    @Autowired
    private ISalesReturnDao salesReturnDao;


    @Override
    public List<SalesReturn> selectSalesReturnList(SalesReturn salesreturn)
    {
        return salesReturnDao.selectSalesReturnList(salesreturn);
    }

    @Override
    public SalesReturn selectSalesReturnById(Long sn)  { return salesReturnDao.selectSalesReturnById(sn); }

    @Override
    public void spSalesReturn(SalesReturn salesreturn) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();

        paramsMap.put("stockoutSn",salesreturn.getStockoutSn() );
        paramsMap.put("itemCode",salesreturn.getItemCode() );
        paramsMap.put("batch",salesreturn.getBatch() );
        paramsMap.put("warehouse",salesreturn.getWarehouse() );
        paramsMap.put("position",salesreturn.getPosition() );
        paramsMap.put("quantity",salesreturn.getQuantity() );
        paramsMap.put("poCode",salesreturn.getPoCode() );
//        paramsMap.put("irradiation",salesreturn.getIrradiation() );
//        paramsMap.put("tpc",salesreturn.getTpc() );
        paramsMap.put("vendorId",salesreturn.getVendorId());
        paramsMap.put("customerId",salesreturn.getCustomerId());
        paramsMap.put("returnDate",salesreturn.getReturnDate());
        paramsMap.put("createBy",ShiroUtils.getLoginName());
        paramsMap.put("remark",salesreturn.getRemark());
        salesReturnDao.spSalesReturn(paramsMap);
    }

    @Override
    public void spSalesReturnRemove(int sn) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();
        paramsMap.put("sn",sn);
        salesReturnDao.spSalesReturnRemove(paramsMap);
    }

}
