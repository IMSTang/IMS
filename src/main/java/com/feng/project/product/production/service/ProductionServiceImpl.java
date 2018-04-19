package com.feng.project.product.production.service;
import com.feng.common.utils.StringUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.product.production.domain.Production;
import com.feng.project.product.production.domain.ProductionSimple;
import com.feng.project.purchase.vendor.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productionService")
public class ProductionServiceImpl implements IProductionService {
    @Autowired
    private IProductionDao productionDao;

    @Override
    public List<Production> selectAllProduction(Production production) {
        return  productionDao.selectProductionAll(production);
    }

    public List<Production> selectProductionList(Production production)
    {
        return productionDao.selectProductionList(production);
    }

    @Override
    public Production selectProductionById(Long id) {
        return productionDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteProductionById(Long id) {
        return productionDao.deleteByPrimaryKey(id);
    }

    @Override
    public int saveProduction(Production production) {

        Long productionId = production.getProductionId();
        int count = 0;
        if (StringUtils.isNotNull(productionId))
        {
            production.setUpdateBy(ShiroUtils.getLoginName());
            // 修改供应商信息
            count = productionDao.updateByPrimaryKeySelective(production);
        }
        else
        {
            production.setCreateBy(ShiroUtils.getLoginName());
            // 新增供应商信息
            count = productionDao.insertSelective(production);
        }
        return count;
    }

    @Override
    public int batchDeleteProduction(Long[] ids) {
        return productionDao.batchDeleteProduction(ids);
    }

    @Override
    public List<ProductionSimple> selectProductionSimpleList(String searchStr, String type) {

        if("code".equalsIgnoreCase(type)) {
            return productionDao.selectProductionSimpleByCode(searchStr);
        }

        if("name".equalsIgnoreCase(type)) {
            return productionDao.selectProductionSimpleByName(searchStr);
        }
        return null;
    }

}
