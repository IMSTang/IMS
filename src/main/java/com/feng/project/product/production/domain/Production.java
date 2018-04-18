package com.feng.project.product.production.domain;
import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Production  extends PageDomain {
        private  Long  productionId;
        private String itemCode;
        private String itemName;
        private String itemNameCn;
        private String productCategory;
        private String specification;
        private String specificationCn;
        private String testMethod;
        private String placeOfOrigin;
        private String efficiency;
        private String safetyStock;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private int status;

        public String getItemCode() {
                return itemCode;
        }

        public void setItemCode(String itemCode) {
                this.itemCode = itemCode;
        }

        public String getItemName() {
                return itemName;
        }

        public void setItemName(String itemName) {
                this.itemName = itemName;
        }

        public String getItemNameCn() {
                return itemNameCn;
        }

        public void setItemNameCn(String itemNameCn) {
                this.itemNameCn = itemNameCn;
        }

        public String getProductCategory() {
                return productCategory;
        }

        public void setProductCategory(String productCategory) {
                this.productCategory = productCategory;
        }

        public String getSpecification() {
                return specification;
        }

        public void setSpecification(String specification) {
                this.specification = specification;
        }

        public String getSpecificationCn() {
                return specificationCn;
        }

        public void setSpecificationCn(String specificationCn) {
                this.specificationCn = specificationCn;
        }

        public String getTestMethod() {
                return testMethod;
        }

        public void setTestMethod(String testMethod) {
                this.testMethod = testMethod;
        }

        public String getPlaceOfOrigin() {
                return placeOfOrigin;
        }

        public void setPlaceOfOrigin(String placeOfOrigin) {
                this.placeOfOrigin = placeOfOrigin;
        }

        public String getEfficiency() {
                return efficiency;
        }

        public void setEfficiency(String efficiency) {
                this.efficiency = efficiency;
        }

        public String getSafetyStock() {
                return safetyStock;
        }

        public void setSafetyStock(String safetyStock) {
                this.safetyStock = safetyStock;
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

        public Long getProductionId() {
                return productionId;
        }

        public void setProductionId(Long productionId) {
                this.productionId = productionId;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }
}
