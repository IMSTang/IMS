package com.feng.project.system.attach.dao;

import java.util.List;

import com.feng.project.system.attach.domain.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Attachment信息 数据层
 * 
 * @author feng
 */
@Mapper
public interface IAttachmentDao
{

    /**
     * 查询 attachment 集合
     * 
     * @param mainType, mainSn 表单类型和主键
     * @return attachment集合
     */
    public List<Attachment> selectAttachmentList(@Param("mainType")String mainType, @Param("mainSn")int mainSn);

    /**
     * 通过 ID查询 Attachment
     *
     * @param attachment Id
     * @return Attachment
     */
    public Attachment selectAttachmentById(int attachmentId);

	/**
     * 通过 id 删除信息
     * 
     * @param attachment ID
     * @return int
     */
    public int deleteAttachmentById(int attachmentId);

}
