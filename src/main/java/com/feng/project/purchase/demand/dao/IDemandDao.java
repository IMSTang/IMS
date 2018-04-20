package com.feng.project.purchase.demand.dao;

import com.feng.project.purchase.demand.domain.Demand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * product Demand信息 数据层
 * 
 * @author feng
 */
@Mapper
public interface IDemandDao
{

    /**
     * 查询product Demand集合
     * 
     * @param demand product Demand信息
     * @return product Demand集合
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
     * @return product Demand信息
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
     * 修改product Demand信息
     * 
     * @param demand product Demand信息
     * @return 结果
     */
    public int updateDemand(Demand demand);

    /**
     * 新增product Demand信息
     * 
     * @param demand product Demand信息
     * @return 结果
     */
    public int insertDemand(Demand demand);

}
