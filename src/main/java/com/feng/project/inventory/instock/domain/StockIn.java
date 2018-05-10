package com.feng.project.inventory.instock.domain;
import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockIn  extends PageDomain {
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
        private List<Attachment> attachmentList;
        /** 产品对象 */
        private Production production;
        /** Vendor对象 */
        private Vendor vendor;

        public int getSn() {
                return sn;
        }

        public void setSn(int sn) {
                this.sn = sn;
        }

        public String getItemCode() {
                return itemCode;
        }

        public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
        }

        public String getBatch() {
                return batch;
        }

        public void setBatch(String batch) {
                this.batch = batch;
        }

        public String getWarehouse() {
                return warehouse;
        }

        public void setWarehouse(String warehouse) {
                this.warehouse = warehouse;
        }

        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public Double getPricePurchase() {
                return pricePurchase;
        }

        public void setPricePurchase(Double pricePurchase) {
                this.pricePurchase = pricePurchase;
        }

        public Double getPriceFobOntario() {
                return priceFobOntario;
        }

        public void setPriceFobOntario(Double priceFobOntario) {
                this.priceFobOntario = priceFobOntario;
        }

        public Double getQuantity() {
                return quantity;
        }

        public void setQuantity(Double quantity) {
                this.quantity = quantity;
        }

        public String getIrradiation() {
                return irradiation;
        }

        public void setIrradiation(String irradiation) {
                this.irradiation = irradiation;
        }

        public String getTpc() {
                return tpc;
        }

        public void setTpc(String tpc) {
                this.tpc = tpc;
        }

        public String getVendorId() {
                return vendorId;
        }

        public void setVendorId(String vendorId) {
                this.vendorId = vendorId;
        }

        public String getCustomerId() {
                return customerId;
        }

        public void setCustomerId(String customerId) {
                this.customerId = customerId;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getStockInDate() {
                return stockInDate;
        }

        public void setStockInDate(String stockInDate) {
                this.stockInDate = stockInDate;
        }

        public String getCreateBy() {
                return createBy;
        }

        public void setCreateBy(String createBy) {
                this.createBy = createBy;
        }

        public String getCreateTime() {
                return createTime;
        }

        public void setCreateTime(String createTime) {
                this.createTime = createTime;
        }

        public String getUpdateBy() {
                return updateBy;
        }

        public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
        }

        public String getUpdateTime() {
                return updateTime;
        }

        public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        public List<Attachment> getAttachmentList() {
                return attachmentList;
        }

        public void setAttachmentList(List<Attachment> attachmentList) {
                this.attachmentList = attachmentList;
        }

        public Production getProduction() {
                return production;
        }

        public void setProduction(Production production) {
                this.production = production;
        }

        public Vendor getVendor() {
                return vendor;
        }

        public void setVendor(Vendor vendor) {
                this.vendor = vendor;
        }
}
