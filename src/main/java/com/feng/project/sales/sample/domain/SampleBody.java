package com.feng.project.sales.sample.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SampleBody extends PageDomain {

    private  Long sampleBodyId;
    private Long sampleId;
    private String  itemCode;
    /**  */
    /**  */
    private String itemName;
    /**  */
    private String price;
    /**  */
    private String quantity;
    private String specification;
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public void setSampleBodyId(Long sampleBodyId) {
//        this.sampleBodyId = sampleBodyId;
//    }
//
//    public void setSampleId(Long sampleId) {
//        this.sampleId = sampleId;
//    }
//
//    public void setItemCode(String itemCode) {
//        this.itemCode = itemCode;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }


//    @Override
//    public String toString() {
//        return "SampleBody{" +
//                "sampleBodyId=" + sampleBodyId +
//                ", sampleId=" + sampleId +
//                ", itemCode=" + itemCode +
//                ", itemName='" + itemName + '\'' +
//                ", price='" + price + '\'' +
//                ", quantity='" + quantity + '\'' +
//                '}';
//    }
}
