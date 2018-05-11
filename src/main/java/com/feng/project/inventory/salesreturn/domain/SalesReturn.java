package com.feng.project.inventory.salesreturn.domain;
import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.sales.customer.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SalesReturn extends PageDomain {
        private Long sn;
        private String returnDate;
        private String itemCode;
        private int stockoutSn;
        private String batch;
        private String warehouse;
        private String position;
        private Double quantity;
        private String irradiation;
        private String tpc;
        private String vendorId;
        private String customerId;
        private int status;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;

        /** 产品对象 */
        private Production production;
        /** Vendor对象 */
        private Vendor vendor;
        /** Vendor对象 */
        private Customer customer;

}
