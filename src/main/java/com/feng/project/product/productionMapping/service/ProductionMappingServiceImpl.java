package com.feng.project.product.productionMapping.service;
import com.feng.common.utils.StringUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.product.production.domain.Production;
import com.feng.project.product.productionMapping.dao.IProductionMappingDao;
import com.feng.project.product.productionMapping.domain.ProductionMapping;
import com.feng.project.purchase.vendor.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productionMappingService")
public class ProductionMappingServiceImpl implements IProductionMappingService {
    @Autowired
    private IProductionMappingDao productionMappingDao;


    @Override
    public List<ProductionMapping> selectProductionMappingList(ProductionMapping productionMapping) {
        return productionMappingDao.selectProductionMappingList(productionMapping);
    }

    @Override
    public int saveProductionMapping(ProductionMapping productionMapping) {

        Long id = productionMapping.getId();
        int count = 0;
        if (StringUtils.isNotNull(id))
        {
            productionMapping.setUpdateBy(ShiroUtils.getLoginName());
            // 修改供应商信息
            count = productionMappingDao.updateProductionMapping(productionMapping);
        }
        else
        {
            productionMapping.setCreateBy(ShiroUtils.getLoginName());
            // 新增供应商信息
            count = productionMappingDao.insertProductionMapping(productionMapping);
        }
        return count;
    }

    @Override
    public ProductionMapping selectProductionMappingById(Long id) {
        return productionMappingDao.selectProductionMappingById(id);
    }

    @Override
    public int deleteProductionMappingById(Long id) {
        return productionMappingDao.deleteById(id);
    }

    @Override
    public int batchDeleteProductionMapping(Long[] ids) {
        return productionMappingDao.batchDeleteProductionMapping(ids);
    }
}
