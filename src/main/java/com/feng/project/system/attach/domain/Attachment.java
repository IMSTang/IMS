package com.feng.project.system.attach.domain;

import lombok.Data;

@Data
public class Attachment {
    private Long attachmentId;
    //OriginalFilename
    private String attachmentName;
    //saved file name
    private String attachmentUuid;
    private int mainSn;
    private String mainType;

//    private String createBy;
//    private String createTime;
//    private String updateTime;
//    private String updateBy;
//    private String remark;


    public Attachment() {
    }
    public Attachment(String attachmentName, String attachmentUuid) {
        this.attachmentName = attachmentName;
        this.attachmentUuid = attachmentUuid;
    }
//    public Attachment(Long attachmentId, String attachmentName, String attachmentUuid, String mainType, int mainSn) {
//        this.attachmentId = attachmentId;
//        this.attachmentName = attachmentName;
//        this.attachmentUuid = attachmentUuid;
//        this.mainType = mainType;
//        this.mainSn = mainSn;
//    }

}
