package com.feng.project.purchase.inquiry.service;

import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.purchase.inquiry.dao.IInquiryDao;
import com.feng.project.purchase.inquiry.domain.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 询价信息 服务层处理
 * 
 * @author feng
 */
@Service("inquiryService")
public class InquiryServiceImpl implements IInquiryService
{
    @Autowired
    private IInquiryDao inquiryDao;

    /**
     * 查询询价信息集合
     * 
     * @param inquiry 询价信息
     * @return 询价信息集合
     */
    @Override
    public List<Inquiry> selectInquiryList(Inquiry inquiry)
    {
        return inquiryDao.selectInquiryList(inquiry);
    }

    /**
     * 查询所有询价
     * 
     * @return 询价列表
     */
    @Override
    public List<Inquiry> selectInquiryAll()
    {
        return inquiryDao.selectInquiryAll();
    }

    /**
     * 通过询价ID查询询价信息
     * 
     * @param inquiryId 询价ID
     * @return 角色对象信息
     */
    @Override
    public Inquiry selectInquiryById(Long inquiryId)
    {
        return inquiryDao.selectInquiryById(inquiryId);
    }

    /**
     * 通过询价ID删除询价信息
     * 
     * @param inquiryId 询价ID
     * @return 结果
     */
    @Override
    public int deleteInquiryById(Long inquiryId)
    {
        return inquiryDao.deleteInquiryById(inquiryId);
    }

    /**
     * 批量删除询价信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteInquiry(Long[] ids)
    {
        return inquiryDao.batchDeleteInquiry(ids);
    }

    /**
     * 保存询价信息
     * 
     * @param inquiry 询价信息
     * @return 结果
     */
    @Override
    public int saveInquiry(Inquiry inquiry)
    {
        Long inquiryId = inquiry.getInquiryId();
        int count = 0;
        if (StringUtils.isNotNull(inquiryId))
        {
            inquiry.setUpdateBy(ShiroUtils.getLoginName());
            // 修改询价信息
            count = inquiryDao.updateInquiry(inquiry);
        }
        else
        {
            inquiry.setCreateBy(ShiroUtils.getLoginName());
            // 新增询价信息
            count = inquiryDao.insertInquiry(inquiry);
        }
        return count;
    }

}
