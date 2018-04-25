package com.feng.project.sales.quote.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuoteBody extends PageDomain {

    private  Long quoteBodyId;
    private Long quoteId;
    private String  itemCode;
    /**  */
    /**  */
    private String itemName;
    /**  */
    private String price;
    /**  */
    private String quantity;

//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public void setQuoteBodyId(Long quoteBodyId) {
//        this.quoteBodyId = quoteBodyId;
//    }
//
//    public void setQuoteId(Long quoteId) {
//        this.quoteId = quoteId;
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
//        return "QuoteBody{" +
//                "quoteBodyId=" + quoteBodyId +
//                ", quoteId=" + quoteId +
//                ", itemCode=" + itemCode +
//                ", itemName='" + itemName + '\'' +
//                ", price='" + price + '\'' +
//                ", quantity='" + quantity + '\'' +
//                '}';
//    }
}
