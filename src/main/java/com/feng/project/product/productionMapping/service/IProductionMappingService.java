package com.feng.project.product.productionMapping.service;

import com.feng.project.product.productionMapping.domain.ProductionMapping;

import java.util.List;

public interface IProductionMappingService {

    public List<ProductionMapping>  selectProductionMappingList(ProductionMapping productionMapping);

    public  int saveProductionMapping(ProductionMapping productionMapping);

    public ProductionMapping  selectProductionMappingById(Long id);

    public int deleteProductionMappingById(Long id);
    public  int batchDeleteProductionMapping(Long[] ids);
}
