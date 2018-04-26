package com.feng.project.purchase.inquiry.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InquiryBody extends PageDomain {

    private  Long inquiryBodyId;
    private Long inquiryId;
    private String  itemCode;
    /**  */
    /**  */
    private String itemName;
    /**  */
    private String price;
    /**  */
    private String quantity;


}
