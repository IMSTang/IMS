package com.feng.project.inventory.instock.service;
import com.feng.common.utils.StringUtils;
import com.feng.project.inventory.instock.dao.IStockInDao;
import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.product.production.domain.Production;
import org.springframework.beans.factory.annotation.Autowired;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stockInService")
public class StockInServiceImpl implements IStockInService {
    @Autowired
    private IStockInDao stockInDao;

    /*
    @Override
    public List<Production> selectAllProduction(Production production) {
        return  productionDao.selectProductionAll(production);
    }
    */


    @Override
    public List<StockIn> selectStockInList(StockIn stockin)
    {
        return stockInDao.selectStockInList(stockin);
    }

    @Override
    public StockIn selectStockInById(Long sn)  { return stockInDao.selectStockInById(sn); }

    @Override
    public void spStockIn(StockIn stockin) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();
        stockin.setCreateBy(ShiroUtils.getLoginName());
        System.out.println("---------save2-----------------");
        System.out.println("---------save2---"+stockin.getBatch()+"-----");
        /*
"warehouse111",
"position111",
10,
10,
50,
"irradiation111",
"tpc111",
111111122,
333333333,
"admin",
"attachment Name 1",
"attachmentURL",
"remark111"
)
         */
        paramsMap.put("itemCode",stockin.getItemCode() );
        paramsMap.put("batch",stockin.getBatch() );
        paramsMap.put("warehouse",stockin.getWarehouse() );
        paramsMap.put("position",stockin.getPosition() );
        paramsMap.put("pricePurchase",stockin.getPricePurchase() );
        paramsMap.put("priceFobOntario",stockin.getPriceFobOntario() );
        paramsMap.put("quantity",stockin.getQuantity() );
        paramsMap.put("irradiation",stockin.getIrradiation());
        paramsMap.put("tpc",stockin.getTpc());
        paramsMap.put("vendorId",stockin.getVendorId());
        paramsMap.put("createBy",stockin.getCreateBy());
        paramsMap.put("attachmentName",stockin.getAttachment());
        paramsMap.put("attachment",stockin.getAttachment());
        paramsMap.put("remark",stockin.getRemark());
        paramsMap.put("result","");

        System.out.println("stockin.getAttachment() ----------   "+stockin.getAttachment());
        stockInDao.spStockIn(paramsMap);

        System.out.println("stockin.spStockIn result ----------   "+paramsMap.get("result").toString());
    }

    @Override
    public String spStockInRemove(int sn) {
        Map<String, Object> paramsMap =  new HashMap<String, Object>();
        String result = "-1";
        paramsMap.put("sn",sn);
        paramsMap.put("result",result);
        stockInDao.spStockInRemove(paramsMap);

        return paramsMap.get("result").toString();
    }

}
