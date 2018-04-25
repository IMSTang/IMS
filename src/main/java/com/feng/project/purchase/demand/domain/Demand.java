package com.feng.project.purchase.demand.domain;

import com.feng.framework.web.page.PageDomain;
import com.feng.project.product.production.domain.Production;
import com.feng.project.system.dept.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商对象 pur_demand
 * pur_demand
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Demand extends PageDomain
{
    /** 供应商ID */
    private Long demandId;
    /**  */
    private String demandDate;
    /**  */
    private String vendorId;
    /**  */
    private String vendorName;
    /**  */
    private String itemCode;
    /**  */
    private Double price;
    /**  */
    private Double quantity;
    /**  */
    private String urgencyDegree;
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
    /**  */
    private String specification;
    /**  */
    private String itemName;
    /** 产品对象 */
    private Production production;

}
