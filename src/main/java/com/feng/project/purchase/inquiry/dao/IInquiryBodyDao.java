package com.feng.project.purchase.inquiry.dao;

import com.feng.project.purchase.inquiry.domain.InquiryBody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IInquiryBodyDao {
   public int  batchInquiryBody(List<InquiryBody> inquiryBodieList);

   /**
    * delete inquiryBody by Id
    * @param inquiryBodyId
    * @return
    */
   public  int deleteInquiryBodyById(Long inquiryBodyId);


   /**
    *
    * @param inquiryId
    * @return
    */
   public int checkInquiryUsed(Long inquiryId);

   public int batchDeleteInquiryBody(Long[] inquiryBodyIds);


   /**
    * get inquiryBody by inquiryId
    * @param inquiryId
    * @return
    */
   public List<InquiryBody> selectBodyByInquiryId(Long inquiryId);



   public  int batchDeleteInquiryBodyOnType(Map<String, Object> param);

   public int updateInquiryBody(InquiryBody inquiryBody);
}
