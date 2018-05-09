package com.feng.project.inventory.queryproduct.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SumInventory extends PageDomain {

    private String  itemCode;

    private String sumQuantity;
    private String pricePurchase;
    private String priceFobOntario;

}