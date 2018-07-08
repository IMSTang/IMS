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


        public void setSn(Long sn) {
                this.sn = sn;
        }

        public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
        }

        public void setBatch(String batch) {
                this.batch = batch;
        }

        public void setWarehouse(String warehouse) {
                this.warehouse = warehouse;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public void setPricePurchase(Double pricePurchase) {
                this.pricePurchase = pricePurchase;
        }

        public void setPriceFobOntario(Double priceFobOntario) {
                this.priceFobOntario = priceFobOntario;
        }

        public void setQuantity(Double quantity) {
                this.quantity = quantity;
        }

        public void setIrradiation(String irradiation) {
                this.irradiation = irradiation;
        }

        public void setTpc(String tpc) {
                this.tpc = tpc;
        }

        public void setVendorId(String vendorId) {
                this.vendorId = vendorId;
        }

        public void setCustomerId(String customerId) {
                this.customerId = customerId;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public void setStockInDate(String stockInDate) {
                this.stockInDate = stockInDate;
        }

        public void setCreateBy(String createBy) {
                this.createBy = createBy;
        }

        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }

        public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
        }

        public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        public void setAttachmentList(List<Attachment> attachmentList) {
                this.attachmentList = attachmentList;
        }

        public void setProduction(Production production) {
                this.production = production;
        }

        public void setVendor(Vendor vendor) {
                this.vendor = vendor;
        }
}
