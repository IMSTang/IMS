package com.feng.project.product.productionMapping.domain;
import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionMapping  extends PageDomain {
        private Long id;
        private String itemCode;
        private String itemName;
        private String newItemCode;
        private String newItemName;
        private String rate;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private Integer status;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

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

        public String getNewItemCode() {
                return newItemCode;
        }

        public void setNewItemCode(String newItemCode) {
                this.newItemCode = newItemCode;
        }

        public String getNewItemName() {
                return newItemName;
        }

        public void setNewItemName(String newItemName) {
                this.newItemName = newItemName;
        }

        public String getRate() {
                return rate;
        }

        public void setRate(String rate) {
                this.rate = rate;
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

        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }
}
