package com.feng.project.purchase.vendor.dao;

import com.feng.project.purchase.vendor.domain.Vendor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 供应商信息 数据层
 * 
 * @author feng
 */
@Mapper
public interface IVendorDao
{

    /**
     * 查询供应商集合
     * 
     * @param vendor 供应商信息
     * @return 供应商集合
     */
    public List<Vendor> selectVendorList(Vendor vendor);

    /**
     * 查询所有供应商
     * 
     * @return 供应商列表
     */
    public List<Vendor> selectVendorAll();


    /**
     * 通过供应商ID查询供应商信息
     * 
     * @param vendorId 供应商ID
     * @return 供应商信息
     */
    public Vendor selectVendorById(Long vendorId);

    /**
     * 通过供应商ID删除供应商信息
     * 
     * @param vendorId 供应商ID
     * @return 结果
     */
    public int deleteVendorById(Long vendorId);

    /**
     * 批量删除供应商信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteVendor(Long[] ids);

    /**
     * 修改供应商信息
     * 
     * @param vendor 供应商信息
     * @return 结果
     */
    public int updateVendor(Vendor vendor);

    /**
     * 新增供应商信息
     * 
     * @param vendor 供应商信息
     * @return 结果
     */
    public int insertVendor(Vendor vendor);

}
