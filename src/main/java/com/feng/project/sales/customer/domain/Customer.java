package com.feng.project.sales.customer.domain;
import com.feng.framework.web.page.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends PageDomain {
    private  int  customerId;
    private  String customerName;
    private  int status;
    private  String firstName;
    private  String lastName;
    private  String middleName;
    private  String nameTitle;
    private  String jobTitle;
    private  String mainPhone;
    private  String workPhone;
    private  String mobile;
    private  String fax;
    private  String mainMail;
    private  String ccMail;
    private  String addressShipTo;
    private  String addressBillTo;
    private  String createBy;
    private  String createTime;
    private  String updateBy;
    private  String updateTime;
    private  String remark;
}
