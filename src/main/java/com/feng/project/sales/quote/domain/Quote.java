package com.feng.project.sales.quote.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 报价实体类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Quote extends PageDomain {

    /** 询价ID */
    private Long quoteId;

    private  String reminder;
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
    private List<QuoteBody> body;

    public List<QuoteBody> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", reminder='" + reminder + '\'' +
                ", quoteBodyId=" + quoteBodyId +
                ", quoteDate='" + quoteDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
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
