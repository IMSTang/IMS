package com.feng.project.inventory.queryinventory.service;

import com.feng.project.inventory.queryinventory.dao.IQueryInventoryDao;
import com.feng.project.inventory.queryinventory.domain.QueryInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存 服务层处理
 * 
 * @author feng
 */
@Service("queryInventoryService")
public class QueryInventoryServiceImpl implements IQueryInventoryService
{
    @Autowired
    private IQueryInventoryDao queryInventoryDao;


    /**
     * 查询库存集合
     * 
     * @param queryInventory 库存对象
     * @return 库存集合
     */
    @Override
    public List<QueryInventory> selectQueryInventoryList(QueryInventory queryInventory)
    {
        return queryInventoryDao.selectQueryInventoryList(queryInventory);
    }

    @Override
    public List<QueryInventory> selectQueryInventoryListByBatch(String batch, String itemCode)
    {
        return queryInventoryDao.selectQueryInventoryListByBatch(batch, itemCode);
    }

    /**
     * 查询库存详细
     * 
     * @param sn
     * @return 库存对象
     */
    @Override
    public QueryInventory selectQueryInventoryById(Long sn)
    {
        return queryInventoryDao.selectQueryInventoryById(sn);
    }
}
