package com.feng.project.inventory.instock.domain;

public class attachment {
    private Long id;
    private String attachmentName;
    private String attachment;

    public attachment(String attachmentName, String attachment) {
        this.attachmentName = attachmentName;
        this.attachment = attachment;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
