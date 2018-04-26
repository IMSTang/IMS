package com.feng.project.purchase.inquiry.service;

import com.feng.project.purchase.inquiry.dao.IInquiryBodyDao;
import com.feng.project.purchase.inquiry.dao.IInquiryDao;
import com.feng.project.purchase.inquiry.domain.InquiryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("inquiryBodyService")
public class InquiryBodyServiceImpl implements  IInquiryBodyService{
    @Autowired
    private IInquiryBodyDao inquiryBodyDao;

    @Autowired
    private IInquiryDao inquiryDao;



    /**
     * delete inquiryBody by Id
     *
     * @param inquiryBodyId
     * @return
     */
    @Override
    public int deleteInquiryBodyById(Long inquiryBodyId,Long inquiryId) {

       int count = inquiryBodyDao.deleteInquiryBodyById(inquiryBodyId);

       if(count >0){
           /**
            * 如果InquiryBody中对应的inquiryId 数量为0 ，删除相应的inquiry（ 将inquiry 的状态改为1）
            */
           int inquiryCount = inquiryBodyDao.checkInquiryUsed(inquiryId);
            if(inquiryCount <=0){
                inquiryDao.deleteInquiryById(inquiryId);
            }
       }
        return count;
    }

    @Override
    public int batchDeleteInquiry(Long[] inquiryBodyId, Long[] inquiryId) {
        int result= inquiryBodyDao.batchDeleteInquiryBody(inquiryBodyId);

//        if(result == (inquiryBodyId.length-1)){
            for (int i=0;i< inquiryId.length;i++){
                int inquiryCount = inquiryBodyDao.checkInquiryUsed(inquiryId[i]);
                if(inquiryCount <=0){
                    inquiryDao.deleteInquiryById(inquiryId[i]);
                }
            }
//        }
        return result;
    }

    /**
     * get inquiryBody by inquiryId
     *
     * @param inquiryId
     * @return
     */
    @Override
    public List<InquiryBody> selectBodyByInquiryId(Long inquiryId) {
        return inquiryBodyDao.selectBodyByInquiryId(inquiryId);
    }
}
