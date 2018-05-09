package com.feng.project.inventory.outstock.dao;

import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.inventory.outstock.domain.Inventory;
import com.feng.project.inventory.outstock.domain.StockOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IStockOutDao {


    public List<StockOut> selectStockOutList(StockOut stockOut);


    public int spStockOut(Map<String, Object> paramsMap);

    public int spStockOutRemove(Map<String, Object> paramsMap);

    public Inventory selectInventoryByInStockId(int id);

    public List<StockOut> search_by_customer_itemcode(@Param("customerId")String customerId, @Param("itemCode")String itemCode);

    /**
     * 查询出库单详细
     *
     * @param sn
     * @return 出库单对象
     */
    public StockOut selectStockOutById(Long sn);


}
