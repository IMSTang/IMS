package com.feng.project.inventory.queryinventory.service;

import com.feng.project.inventory.queryinventory.domain.QueryInventory;

import java.util.List;

/**
 * 库存 服务层
 * 
 * @author feng
 */
public interface IQueryInventoryService
{

    /**
     * 查询库存集合
     * 
     * @param queryInventory 库存对象
     * @return 库存集合
     */
    public List<QueryInventory> selectQueryInventoryList(QueryInventory queryInventory);


    public List<QueryInventory> selectQueryInventoryListByBatch(String batch, String itemCode);
    public List<QueryInventory> selectQueryInventoryListEqualBatch(String batch, String itemCode);

    public List<QueryInventory> selectQueryInventoryListByItemCode(String itemCode);
    /**
     * 查询库存详细
     * 
     * @param sn 操作ID
     * @return 库存对象
     */
    public QueryInventory selectQueryInventoryById(Long sn);

    /**
     * check ItemCode Batch Unique
     * @param itemCode
     * @param batch
     * @return flag
     */
    public  String checkItemBatchUnique(String itemCode, String batch);
}
