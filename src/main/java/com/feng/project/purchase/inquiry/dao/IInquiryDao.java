package com.feng.project.purchase.inquiry.dao;

import com.feng.project.purchase.inquiry.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IInquiryDao {

    public List<Inquiry> selectInquiryList(Inquiry inquiry);

    /**
     * 插入 inquiry
     * @param inquiry
     * @return
     */
    public int insertInquiry(Inquiry inquiry);


    /**
     * delete inquiry by Id
     * @param inquiryId
     * @return
     */
    public int deleteInquiryById(Long inquiryId);


    /**
     * get inquiry by id
     * @param inquiryId
     * @return
     */
    public Inquiry selectInquiryById(Long inquiryId);


    /**
     * update inquiry
     * @param inquiry
     * @return
     */
    public int updateInquiry(Inquiry inquiry);
}
