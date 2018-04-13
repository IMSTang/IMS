package com.feng.project.purchase.vendor.service;

import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.purchase.vendor.dao.IVendorDao;
import com.feng.project.purchase.vendor.domain.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商信息 服务层处理
 * 
 * @author feng
 */
@Service("vendorService")
public class VendorServiceImpl implements IVendorService
{
    @Autowired
    private IVendorDao vendorDao;

    /**
     * 查询供应商信息集合
     * 
     * @param vendor 供应商信息
     * @return 供应商信息集合
     */
    @Override
    public List<Vendor> selectVendorList(Vendor vendor)
    {
        return vendorDao.selectVendorList(vendor);
    }

    /**
     * 查询所有供应商
     * 
     * @return 供应商列表
     */
    @Override
    public List<Vendor> selectVendorAll()
    {
        return vendorDao.selectVendorAll();
    }

    /**
     * 通过供应商ID查询供应商信息
     * 
     * @param vendorId 供应商ID
     * @return 角色对象信息
     */
    @Override
    public Vendor selectVendorById(Long vendorId)
    {
        return vendorDao.selectVendorById(vendorId);
    }

    /**
     * 通过供应商ID删除供应商信息
     * 
     * @param vendorId 供应商ID
     * @return 结果
     */
    @Override
    public int deleteVendorById(Long vendorId)
    {
        return vendorDao.deleteVendorById(vendorId);
    }

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteVendor(Long[] ids)
    {
        return vendorDao.batchDeleteVendor(ids);
    }

    /**
     * 保存供应商信息
     * 
     * @param vendor 供应商信息
     * @return 结果
     */
    @Override
    public int saveVendor(Vendor vendor)
    {
        Long vendorId = vendor.getVendorId();
        int count = 0;
        if (StringUtils.isNotNull(vendorId))
        {
            vendor.setUpdateBy(ShiroUtils.getLoginName());
            // 修改供应商信息
            count = vendorDao.updateVendor(vendor);
        }
        else
        {
            vendor.setCreateBy(ShiroUtils.getLoginName());
            // 新增供应商信息
            count = vendorDao.insertVendor(vendor);
        }
        return count;
    }

}
