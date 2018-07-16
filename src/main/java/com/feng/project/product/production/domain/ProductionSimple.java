package com.feng.project.product.production.domain;
import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionSimple extends PageDomain {
        private Long productionId;
        private String itemCode;
        private String itemName;
        private String specification;

        public String getSpecification() {
                return specification;
        }

        public void setSpecification(String specification) {
                this.specification = specification;
        }

        public Long getProductionId() {
                return productionId;
        }

        public void setProductionId(Long productionId) {
                this.productionId = productionId;
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


}
