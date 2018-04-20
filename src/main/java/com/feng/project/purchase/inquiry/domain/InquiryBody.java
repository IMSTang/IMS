package com.feng.project.purchase.inquiry.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 询价对象 pur_inquiry
 * pur_inquiry_body
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InquiryBody extends PageDomain
{
    /** 询价ID */
    private Long inquiryBodyId;
    /** 询价ID */
    private Long inquiryId;
    /**  */
    private String itemCode;
    /**  */
    private String itemName;
    /**  */
    private String price;
    /**  */
    private String quantity;

}
