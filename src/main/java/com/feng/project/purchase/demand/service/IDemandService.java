package com.feng.project.purchase.demand.service;

import com.feng.project.purchase.demand.domain.Demand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * product Demand信息 服务层
 * 
 * @author feng
 */
public interface IDemandService
{
    /**
     * 查询product Demand信息集合
     * 
     * @param demand product Demand信息
     * @return product Demand信息集合
     */
    public List<Demand> selectDemandList(Demand demand);

    /**
     * 查询所有product Demand
     * 
     * @return product Demand列表
     */
    public List<Demand> selectDemandAll();


    /**
     * 通过product DemandID查询product Demand信息
     * 
     * @param demandId product DemandID
     * @return 角色对象信息
     */
    public Demand selectDemandById(Long demandId);

    /**
     * 通过product DemandID删除product Demand信息
     * 
     * @param demandId product DemandID
     * @return 结果
     */
    public int deleteDemandById(Long demandId);

    /**
     * 批量删除product Demand信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteDemand(Long[] ids);

    /**
     * 保存product Demand信息
     * 
     * @param demand product Demand信息
     * @return 结果
     */
    public int saveDemand(Demand demand);

}
