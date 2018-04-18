package com.feng.project.purchase.inquiry.service;

import com.feng.project.purchase.inquiry.domain.Inquiry;

import java.util.List;

/**
 * 询价信息 服务层
 * 
 * @author feng
 */
public interface IInquiryService
{
    /**
     * 查询询价信息集合
     * 
     * @param inquiry 询价信息
     * @return 询价信息集合
     */
    public List<Inquiry> selectInquiryList(Inquiry inquiry);

    /**
     * 查询所有询价
     * 
     * @return 询价列表
     */
    public List<Inquiry> selectInquiryAll();


    /**
     * 通过询价ID查询询价信息
     * 
     * @param inquiryId 询价ID
     * @return 角色对象信息
     */
    public Inquiry selectInquiryById(Long inquiryId);

    /**
     * 通过询价ID删除询价信息
     * 
     * @param inquiryId 询价ID
     * @return 结果
     */
    public int deleteInquiryById(Long inquiryId);

    /**
     * 批量删除询价信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteInquiry(Long[] ids);

    /**
     * 保存询价信息
     * 
     * @param inquiry 询价信息
     * @return 结果
     */
    public int saveInquiry(Inquiry inquiry);
}
