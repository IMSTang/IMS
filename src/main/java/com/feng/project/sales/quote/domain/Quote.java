package com.feng.project.sales.quote.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报价实体类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Quote extends PageDomain {

    /** 询价ID */
    private Long quoteId;

    private  int reminder;
    private Long quoteBodyId;
    /**  */
    private String quoteDate;
    /**  */
    private String customerId;
    /**  */
    private String customerName;
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

}
