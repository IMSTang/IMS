package com.feng.project.sales.customer.domain;
import lombok.Data;

@Data
public class Customer {
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
    private  String description;
    private  String createBy;
    private  String createTime;
    private  String updateBy;
    private  String updateTime;
    private  String remark;
}
