package com.feng.project.inventory.queryproduct.domain;

import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存记录 queryproduct
 * 
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryProduct extends PageDomain
{
    private  Long  productionId;
    private String itemCode;
    private String itemName;
    private String itemNameCn;
    private String productCategory;
    private String specification;
    private String specificationCn;
//    private String testMethod;
//    private String placeOfOrigin;
//    private String efficiency;
    private String safetyStock;
    private String latestDemandDate;

    private SumInventory sumInventory;

}
