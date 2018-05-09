package com.feng.project.inventory.queryinventory.domain;

import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.sales.customer.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 库存记录 queryinventory
 * 
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryInventory extends PageDomain
{
    private int sn;
    private String itemCode;
    private String batch;
    private String warehouse;
    private String position;
    private Double pricePurchase;
    private Double priceFobOntario;
    private Double quantity;
    private String irradiation;
    private String tpc;
    private String vendorId;
    private String customerId;
    private int status;
    private String stockInDate;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;

    /** 产品对象 */
    private Production production;
    /** Vendor对象 */
    private Vendor vendor;

//    private String itemName;
//    private String vendorName;
//    private String customerName;
}
