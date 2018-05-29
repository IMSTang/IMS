package com.feng.project.purchase.inquiry.service;

import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.purchase.inquiry.dao.IInquiryBodyDao;
import com.feng.project.purchase.inquiry.dao.IInquiryDao;
import com.feng.project.purchase.inquiry.domain.Inquiry;
import com.feng.project.purchase.inquiry.domain.InquiryBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("inquiryService")
public class InquiryServiceImpl implements  IInquiryService{
    @Autowired
    private IInquiryDao inquiryDao;

    @Autowired
    private IInquiryBodyDao inquiryBodyDao;

    @Autowired
    private IProductionDao productionDao;

    @Override
    public List<Inquiry> selectInquiryList(Inquiry inquiry) {
        return inquiryDao.selectInquiryList(inquiry);
    }

    @Override
    public String selectMinMaxPriceByItemCode(String itemCode){
        return inquiryDao.selectMinMaxPriceByItemCode(itemCode);
    }

    /**
     * 插入 inquiry
     *
     * @param inquiry
     * @return
     */
    @Override
    public int insertInquiry(Inquiry inquiry) {

        //判断 Item Code 是否存在
        int ItemCodeNum=0;
        for (int i=0;i<inquiry.getBody().size();i++){
            System.out.println();
            ItemCodeNum= productionDao.checkItemCodeUnique(inquiry.getBody().get(i).getItemCode());
            if( ItemCodeNum<=0){

                return -1;
            }
        }

        Long inquiryId = inquiry.getInquiryId();
        int count=0;
        if (StringUtils.isNotNull(inquiryId))
        {
            //update
            inquiry.setUpdateBy(ShiroUtils.getLoginName());
            count =inquiryDao.updateInquiry(inquiry);
            //批量删除不在body里的 inquiryBody
            Map map=new HashMap<String, Object>();
            map.put("inquiryId",inquiryId);
            List<String> list=new ArrayList<String>();

            for (int i=0;i<inquiry.getBody().size();i++){
                InquiryBody inquiryBody = inquiry.getBody().get(i);
                Long inquiryBodyId = inquiryBody.getInquiryBodyId();
                if(StringUtils.isNotNull(inquiryBodyId)){
                    list.add(""+inquiryBodyId);
                }
            }
            int size=list.size();
            String[] array = (String[])list.toArray(new String[size]);
            map.put("ids", array);
            inquiryBodyDao.batchDeleteInquiryBodyOnType(map);

            //更新inquiryItem
            for (int i=0;i<inquiry.getBody().size();i++){
                InquiryBody inquiryBody = inquiry.getBody().get(i);
                Long inquiryBodyId = inquiryBody.getInquiryBodyId();
                if(StringUtils.isNotNull(inquiryBodyId)){
                    count = inquiryBodyDao.updateInquiryBody(inquiryBody);
                }
                else {
                    List<InquiryBody> list1 = new ArrayList<InquiryBody>();
                    inquiryBody.setInquiryId(inquiryId);
                    list1.add(inquiryBody);
                    count = inquiryBodyDao.batchInquiryBody(list1);
                }
            }
        }else {
            //insert
            String loginName=ShiroUtils.getLoginName();
            inquiry.setCreateBy(loginName);
             count = inquiryDao.insertInquiry(inquiry);

            /**
             *
             */
            if(count >0) {
                insertInquiryBody(inquiry);
            }
        }
        return count;
    }

    public  void insertInquiryBody(Inquiry inquiry){

        List<InquiryBody> list = new ArrayList<InquiryBody>();

        for (int i=0;i<inquiry.getBody().size();i++){

            inquiry.getBody().get(i).setInquiryId(inquiry.getInquiryId());
            list.add(inquiry.getBody().get(i));
        }

        if(list.size()>0){
            inquiryBodyDao.batchInquiryBody(list);
        }
    }


    /**
     * delete inquiry by Id
     *
     * @param inquiryId
     * @return
     */
    @Override
    public int deleteInquiryById(Long inquiryId) {
        return inquiryDao.deleteInquiryById(inquiryId);
    }


    /**
     * get inquiry by id
     *
     * @param inquiryId
     * @return
     */
    @Override
    public Inquiry selectInquiryById(Long inquiryId) {
        return inquiryDao.selectInquiryById(inquiryId);
    }
}
