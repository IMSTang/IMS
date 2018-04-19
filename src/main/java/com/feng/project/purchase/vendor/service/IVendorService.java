package com.feng.project.purchase.vendor.service;

import com.feng.project.purchase.vendor.domain.Vendor;
import com.feng.project.purchase.vendor.domain.VendorIdName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商信息 服务层
 * 
 * @author feng
 */
public interface IVendorService
{
    /**
     * 查询供应商信息集合
     * 
     * @param vendor 供应商信息
     * @return 供应商信息集合
     */
    public List<Vendor> selectVendorList(Vendor vendor);
    /**
     * 查询供应商Id Name集合
     *
     * @param vendor 供应商Id Name信息
     * @return 供应商Id Name信息集合
     */
    public List<VendorIdName> selectVendorIdName(String vName);

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
     * @return 角色对象信息
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
     * 保存供应商信息
     * 
     * @param vendor 供应商信息
     * @return 结果
     */
    public int saveVendor(Vendor vendor);

    /**
     * check vendor Name Unique
     * @param customerName vendor Name
     * @return flag number
     */
    public  String checkNameUnique(String  customerName);

}
