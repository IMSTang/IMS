package com.feng.project.product.production.dao;

import com.feng.project.product.production.domain.ProductionSimple;
import com.feng.project.purchase.vendor.domain.Vendor;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.feng.project.product.production.domain.Production;
@Mapper
public interface IProductionDao {

    public List<Production> selectProductionAll(Production production);
    public List<Production> selectProductionList(Production production);

    public int deleteByPrimaryKey(Long id);

    public int batchDeleteProduction(Long[] id);

    public int insert(Production record);

    public int insertSelective(Production record);

    public Production selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(Production record);

    public int updateByPrimaryKey(Production record);


    public List<ProductionSimple> selectProductionSimpleByCode(String searchStr);
    public List<ProductionSimple> selectProductionSimpleByName(String searchStr);

    public int checkItemCodeUnique(String itemCode);
    public int checkItemCodeIsUsed(Long id);
}
