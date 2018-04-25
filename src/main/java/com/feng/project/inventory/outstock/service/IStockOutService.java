package com.feng.project.inventory.outstock.service;
import java.util.List;

import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.inventory.outstock.domain.Inventory;
import com.feng.project.inventory.outstock.domain.StockOut;

public interface IStockOutService {

    /*
    public List<Production> selectAllProduction(Production production);
    */


    public List<StockOut> selectStockOutList(StockOut stockout);

    public void spStockOut(StockOut stockout);

    public void spStockOutRemove(int sn);

    public Inventory selectInventoryByInStockId(int sn);
}
