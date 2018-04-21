package com.feng.project.purchase.vendor.domain;

import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商对象 pur_vendor
 * pur_vendor
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Vendor extends PageDomain
{
    /** 供应商ID */
    private Long vendorId;
    /**  */
    private String vendorName;
    /**  */
    private String productCategory;
    /**  */
    private String firstName;
    /**  */
    private String lastName;
    /**  */
    private String middleName;
    /**  */
    private String nameTitle;
    /**  */
    private String jobTitle;
    /**  */
    private String mainPhone;
    /**  */
    private String workPhone;
    /**  */
    private String mobile;
    /**  */
    private String fax;
    /**  */
    private String mainMail;
    /**  */
    private String ccMail;
    /**  */
    private String addressShipFrom;
    /**  */
    private String addressBillFrom;
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
    /** 联系人 */
    private String contacts;

}
