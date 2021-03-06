package com.feng.project.inventory.queryproduct.dao;

import com.feng.project.inventory.queryproduct.domain.QueryProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存 数据层
 * 
 * @author feng
 */
@Mapper
public interface IQueryProductDao
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
     * @param sn
     * @return 库存对象
     */
    public QueryProduct selectQueryProductById(Long sn);

    /**
     * 批量增加product Demand信息
     *
     * @param itemCodes 需要增加的itemCode 数组
     * @return 结果
     */
    public int batchDemand(@Param("createBy")String createBy, @Param("arrayItemCode")String[] arrayItemCode);
}
