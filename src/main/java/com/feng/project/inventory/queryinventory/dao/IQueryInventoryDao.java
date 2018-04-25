package com.feng.project.inventory.queryinventory.dao;

import com.feng.project.inventory.queryinventory.domain.QueryInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 库存 数据层
 * 
 * @author feng
 */
@Mapper
public interface IQueryInventoryDao
{

    /**
     * 查询库存集合
     * 
     * @param queryInventory 库存对象
     * @return 库存集合
     */
    public List<QueryInventory> selectQueryInventoryList(QueryInventory queryInventory);
    
    /**
     * 查询库存详细
     * 
     * @param sn
     * @return 库存对象
     */
    public QueryInventory selectQueryInventoryById(Long sn);
}
