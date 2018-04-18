package com.feng.project.purchase.inquiry.dao;

import com.feng.project.purchase.inquiry.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 询价信息 数据层
 * 
 * @author feng
 */
@Mapper
public interface IInquiryDao
{

    /**
     * 查询询价集合
     * 
     * @param inquiry 询价信息
     * @return 询价集合
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
     * @return 询价信息
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
     * 修改询价信息
     * 
     * @param inquiry 询价信息
     * @return 结果
     */
    public int updateInquiry(Inquiry inquiry);

    /**
     * 新增询价信息
     * 
     * @param inquiry 询价信息
     * @return 结果
     */
    public int insertInquiry(Inquiry inquiry);

}
