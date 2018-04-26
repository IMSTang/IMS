package com.feng.project.purchase.inquiry.service;

import com.feng.project.purchase.inquiry.domain.InquiryBody;

import java.util.List;

public interface IInquiryBodyService {
    /**
     * delete inquiryBody by Id
     * @param inquiryBodyId
     * @return
     */
    public  int deleteInquiryBodyById(Long inquiryBodyId,Long inquiryId);

    public  int  batchDeleteInquiry(Long[] inquiryBodyId,Long[] inquiryId);



    /**
     * get inquiryBody by inquiryId
     * @param inquiryId
     * @return
     */
    public List<InquiryBody> selectBodyByInquiryId(Long inquiryId);
}
