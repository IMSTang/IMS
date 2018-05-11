package com.feng.project.system.attach.service;

import java.util.List;
import com.feng.project.system.attach.domain.Attachment;

/**
 * Attachment 服务层
 * 
 * @author feng
 */
public interface IAttachmentService
{

    /**
     * 查询 Attachment 集合
     * 
     * @param mainType, mainSn 表单类型和主键
     * @return Attachment 集合
     */
    public List<Attachment> selectAttachmentList(String mainType, int mainSn);

    /**
     * 通过 ID查询 Attachment
     *
     * @param attachment Id
     * @return Attachment
     */
    public Attachment selectAttachmentById(int attachmentId);

    /**
     * 删除
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int deleteAttachmentById(int id);
}
