package com.feng.project.inventory.instock.dao;

import com.feng.project.inventory.instock.domain.StockIn;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface IStockInDao {


    public List<StockIn> selectStockInList(StockIn stockIn);


    public int spStockIn(Map<String, Object> paramsMap);

    public int spStockInRemove(Map<String, Object> paramsMap);

    /**
     * 查询入库单详细
     *
     * @param sn
     * @return 入库单对象
     */
    public StockIn selectStockInById(Long sn);

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
