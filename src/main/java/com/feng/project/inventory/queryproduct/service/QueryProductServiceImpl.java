package com.feng.project.inventory.queryproduct.service;

import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.inventory.queryproduct.dao.IQueryProductDao;
import com.feng.project.inventory.queryproduct.domain.QueryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存 服务层处理
 * 
 * @author feng
 */
@Service("queryProductService")
public class QueryProductServiceImpl implements IQueryProductService
{
    @Autowired
    private IQueryProductDao queryProductDao;


    /**
     * 查询库存集合
     * 
     * @param queryProduct 库存对象
     * @return 库存集合
     */
    @Override
    public List<QueryProduct> selectQueryProductList(QueryProduct queryProduct)
    {
        return queryProductDao.selectQueryProductList(queryProduct);
    }


    /**
     * 查询库存详细
     * 
     * @param sn
     * @return 库存对象
     */
    @Override
    public QueryProduct selectQueryProductById(Long sn)
    {
        return queryProductDao.selectQueryProductById(sn);
    }

    /**
     * 批量增加product Demand信息
     *
     * @param itemCodes 需要增加的itemCode 数组
     * @return 结果
     */
    @Override
    public int batchDemand(String[] arrayItemCode)
    {
        return queryProductDao.batchDemand(ShiroUtils.getLoginName(), arrayItemCode);
    }
}
