package com.feng.project.system.attach.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feng.project.system.attach.dao.IAttachmentDao;
import com.feng.project.system.attach.domain.Attachment;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author feng
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService
{

    @Autowired
    private IAttachmentDao attachmentDao;


    /**
     * 查询 Attachment 集合
     * 
     * @param mainType, mainSn 表单类型和主键
     * @return Attachment 集合
     */
    @Override
    public List<Attachment> selectAttachmentList(String mainType, int mainSn)
    {
        return attachmentDao.selectAttachmentList(mainType, mainSn);
    }

    /**
     * 通过 ID查询 Attachment
     *
     * @param attachment Id
     * @return Attachment
     */
    public Attachment selectAttachmentById(int attachmentId){
        return attachmentDao.selectAttachmentById(attachmentId);
    }

    /**
     * 删除 
     * 
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteAttachmentById(int attachmentId)
    {
        return attachmentDao.deleteAttachmentById(attachmentId);
    }
}
