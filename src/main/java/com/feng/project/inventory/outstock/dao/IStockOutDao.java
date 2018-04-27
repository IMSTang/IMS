package com.feng.project.inventory.outstock.dao;

import com.feng.project.inventory.instock.domain.StockIn;
import com.feng.project.inventory.outstock.domain.Inventory;
import com.feng.project.inventory.outstock.domain.StockOut;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface IStockOutDao {


    public List<StockOut> selectStockOutList(StockOut stockOut);


    public int spStockOut(Map<String, Object> paramsMap);

    public int spStockOutRemove(Map<String, Object> paramsMap);

    public Inventory selectInventoryByInStockId(int id);

    /**
     * 查询出库单详细
     *
     * @param sn
     * @return 出库单对象
     */
    public StockOut selectStockOutById(Long sn);


    /*
    public List<Production> selectProductionAll(Production production);
    public List<Production> selectProductionList(Production production);

    public int deleteByPrimaryKey(Long id);

    public int batchDeleteProduction(Long[] id);

    public int insert(Production record);

    public int insertSelective(Production record);

    public Production selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(Production record);

    public int updateByPrimaryKey(Production record);
*/


}
