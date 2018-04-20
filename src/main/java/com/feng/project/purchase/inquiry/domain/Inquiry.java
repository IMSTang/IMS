package com.feng.project.purchase.inquiry.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 询价对象 pur_inquiry
 * pur_inquiry
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Inquiry extends PageDomain
{
    /** 询价ID */
    private Long inquiryId;
    /**  */
    private String inquiryDate;
    /**  */
    private String vendorId;
    /**  */
    private String vendorName;
    /**  */
    private String reminder;
    /**  */
    private Long inquiryBodyId;
    /**  */
    private String itemCode;
    /**  */
    private String itemName;
    /**  */
    private String price;
    /**  */
    private String quantity;
    /** 状态（0正常 1停用） */
    private int status;
    /** 创建者 */
    private String createBy;
    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String updateTime;
    /** 更新者 */
    private String updateBy;
    /** 备注 */
    private String remark;
    /** Body */
    private List<InquiryBody> body;

}
