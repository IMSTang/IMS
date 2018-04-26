package com.feng.project.inventory.outstock.domain;
import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.purchase.vendor.domain.Vendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends PageDomain {
        private int sn;
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
        private String quantityStockOut;

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

        public String getPricePurchase() {
                return pricePurchase;
        }

        public void setPricePurchase(String pricePurchase) {
                this.pricePurchase = pricePurchase;
        }

        public String getPriceFobOntario() {
                return priceFobOntario;
        }

        public void setPriceFobOntario(String priceFobOntario) {
                this.priceFobOntario = priceFobOntario;
        }

        public String getQuantity() {
                return quantity;
        }

        public void setQuantity(String quantity) {
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


        public String getQuantityStockOut() {
                return quantityStockOut;
        }

        public void setQuantityStockOut(String quantityStockOut) {
                this.quantityStockOut = quantityStockOut;
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
