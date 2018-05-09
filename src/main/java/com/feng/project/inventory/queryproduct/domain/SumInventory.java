package com.feng.project.inventory.queryproduct.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SumInventory extends PageDomain {

    private String  itemCode;

    private Double sumQuantity;
    private Double pricePurchase;
    private Double priceFobOntario;

}