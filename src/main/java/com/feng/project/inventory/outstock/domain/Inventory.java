package com.feng.project.inventory.outstock.domain;
import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends PageDomain {
        private Long sn;
        private String itemCode;
        private String batch;
        private String warehouse;
        private String position;
        private String pricePurchase;
        private String priceFobOntario;
        private String quantity;
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
//        private String quantityStockOut;

        private Production production;
        /** Vendor对象 */
        private Vendor vendor;

}
