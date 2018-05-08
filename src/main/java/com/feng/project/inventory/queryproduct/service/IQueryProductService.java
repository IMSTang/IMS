package com.feng.project.inventory.queryproduct.service;

import com.feng.project.inventory.queryproduct.domain.QueryProduct;
import java.util.List;

/**
 * 库存 服务层
 * 
 * @author feng
 */
public interface IQueryProductService
{

    /**
     * 查询库存集合
     * 
     * @param queryProduct 库存对象
     * @return 库存集合
     */
    public List<QueryProduct> selectQueryProductList(QueryProduct queryProduct);

    /**
     * 查询库存详细
     * 
     * @param sn 操作ID
     * @return 库存对象
     */
    public QueryProduct selectQueryProductById(Long sn);
}
