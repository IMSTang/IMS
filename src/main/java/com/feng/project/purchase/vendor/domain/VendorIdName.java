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
public class VendorIdName extends PageDomain
{
    /** 供应商ID */
    private Long vendorId;
    /**  */
    private String vendorName;
}
