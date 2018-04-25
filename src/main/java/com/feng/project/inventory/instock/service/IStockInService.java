package com.feng.project.inventory.instock.service;


import com.feng.project.inventory.instock.domain.StockIn;

import java.util.List;

public interface IStockInService {

    /*
    public List<Production> selectAllProduction(Production production);
    */

    public List<StockIn> selectStockInList(StockIn stockin);

    public void spStockIn(StockIn stockin);


    public void spStockInRemove(int sn);
}
