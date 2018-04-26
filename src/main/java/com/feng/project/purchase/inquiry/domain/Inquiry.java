package com.feng.project.purchase.inquiry.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 询价实体类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Inquiry extends PageDomain {

    /** 询价ID */
    private Long inquiryId;

    private  String reminder;
    private Long inquiryBodyId;
    /**  */
    private String inquiryDate;
    /**  */
    private String vendorId;
    /**  */
    private String vendorName;
    /**  */
    private String itemCode;
    /**  */
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
    private List<InquiryBody> body;

    public List<InquiryBody> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "inquiryId=" + inquiryId +
                ", reminder='" + reminder + '\'' +
                ", inquiryBodyId=" + inquiryBodyId +
                ", inquiryDate='" + inquiryDate + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", status=" + status +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", remark='" + remark + '\'' +
                ", body=" + body +
                '}';
    }
}
