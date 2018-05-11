package com.feng.project.inventory.instock.domain;
import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.system.attach.domain.Attachment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockIn  extends PageDomain {
        private Long sn;
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
        private List<Attachment> attachmentList;
        /** 产品对象 */
        private Production production;
        /** Vendor对象 */
        private Vendor vendor;

}
