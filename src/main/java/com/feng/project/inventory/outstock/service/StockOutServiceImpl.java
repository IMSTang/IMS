package com.feng.project.inventory.outstock.service;
import com.feng.common.utils.StringUtils;
import com.feng.project.inventory.outstock.dao.IStockOutDao;
import com.feng.project.inventory.outstock.domain.Inventory;
import com.feng.project.inventory.outstock.domain.StockOut;
import org.springframework.beans.factory.annotation.Autowired;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stockOutService")
public class StockOutServiceImpl implements IStockOutService {
    @Autowired
    private IStockOutDao stockOutDao;


    @Override
    public List<StockOut> selectStockOutList(StockOut stockout)
    {
        return stockOutDao.selectStockOutList(stockout);
    }

    @Override
    public StockOut selectStockOutById(Long sn)  { return stockOutDao.selectStockOutById(sn); }

    @Override
    public void spStockOut(StockOut stockout) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();
        stockout.setCreateBy(ShiroUtils.getLoginName());
        paramsMap.put("sn",stockout.getSn() );
        paramsMap.put("itemCode",stockout.getItemCode() );
        paramsMap.put("batch",stockout.getBatch() );
        paramsMap.put("warehouse",stockout.getWarehouse() );
        paramsMap.put("position",stockout.getPosition() );
        paramsMap.put("quantity",stockout.getQuantity() );
        paramsMap.put("customerId",stockout.getCustomerId());
        paramsMap.put("createBy",stockout.getCreateBy());
        paramsMap.put("remark",stockout.getRemark());
        stockOutDao.spStockOut(paramsMap);
    }

    @Override
    public void spStockOutRemove(int sn) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();
        paramsMap.put("sn",sn);
        stockOutDao.spStockOutRemove(paramsMap);
    }

    @Override
    public Inventory selectInventoryByInStockId(int sn) {
        return stockOutDao.selectInventoryByInStockId(sn);
    }

}
