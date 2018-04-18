package com.feng.project.product.production.service;
import java.util.List;
import com.feng.project.product.production.domain.Production;

public interface IProductionService {

    public List<Production> selectAllProduction(Production production);
    public List<Production>  selectProductionList(Production production);
    public Production selectProductionById(Long id);
    public int deleteProductionById(Long id);
    public  int saveProduction(Production production);
    public  int batchDeleteProduction(Long[] ids);

}
