package com.feng.project.product.productionMapping.dao;
import com.feng.project.product.productionMapping.domain.ProductionMapping;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface IProductionMappingDao {

    public List<ProductionMapping> selectProductionMappingList(ProductionMapping productionmapping);

    public int updateProductionMapping (ProductionMapping productionmapping);

    public int insertProductionMapping (ProductionMapping productionmapping);

    public ProductionMapping selectProductionMappingById(Long id);

    public int deleteById(Long id);

    public int batchDeleteProductionMapping(Long[] id);
}
