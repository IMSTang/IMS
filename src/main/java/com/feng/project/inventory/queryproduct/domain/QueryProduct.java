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
//    private String productCategory;
    private String itemCode;
//	private String itemName;
//	private String itemNameCn;
//	private String specification;
//	private String safetyStock;
    private String sumQuantity;
    private String pricePurchase;
    private String priceFobOntario;

    /** 产品对象 */
    private Production production;
}
