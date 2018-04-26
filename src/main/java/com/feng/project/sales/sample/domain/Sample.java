package com.feng.project.sales.sample.domain;


import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 报价实体类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Sample extends PageDomain {

    /** 询价ID */
    private Long sampleId;

    private  String reminder;
    private Long sampleBodyId;
    /**  */
    private String sampleDate;
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
    private List<SampleBody> body;

    public List<SampleBody> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "sampleId=" + sampleId +
                ", reminder='" + reminder + '\'' +
                ", sampleBodyId=" + sampleBodyId +
                ", sampleDate='" + sampleDate + '\'' +
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
