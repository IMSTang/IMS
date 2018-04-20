package com.feng.project.purchase.demand.service;

import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.purchase.demand.dao.IDemandDao;
import com.feng.project.purchase.demand.domain.Demand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * product Demand信息 服务层处理
 * 
 * @author feng
 */
@Service("demandService")
public class DemandServiceImpl implements IDemandService
{
    @Autowired
    private IDemandDao demandDao;

    /**
     * 查询product Demand信息集合
     * 
     * @param demand product Demand信息
     * @return product Demand信息集合
     */
    @Override
    public List<Demand> selectDemandList(Demand demand)
    {
        return demandDao.selectDemandList(demand);
    }
	
    /**
     * 查询所有product Demand
     * 
     * @return product Demand列表
     */
    @Override
    public List<Demand> selectDemandAll()
    {
        return demandDao.selectDemandAll();
    }

    /**
     * 通过product DemandID查询product Demand信息
     * 
     * @param demandId product DemandID
     * @return 角色对象信息
     */
    @Override
    public Demand selectDemandById(Long demandId)
    {
        return demandDao.selectDemandById(demandId);
    }

    /**
     * 通过product DemandID删除product Demand信息
     * 
     * @param demandId product DemandID
     * @return 结果
     */
    @Override
    public int deleteDemandById(Long demandId)
    {
        return demandDao.deleteDemandById(demandId);
    }

    /**
     * 批量删除product Demand信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteDemand(Long[] ids)
    {
        return demandDao.batchDeleteDemand(ids);
    }

    /**
     * 保存product Demand信息
     * 
     * @param demand product Demand信息
     * @return 结果
     */
    @Override
    public int saveDemand(Demand demand)
    {
        Long demandId = demand.getDemandId();
        int count = 0;
        if (StringUtils.isNotNull(demandId))
        {
            demand.setUpdateBy(ShiroUtils.getLoginName());
            // 修改product Demand信息
            count = demandDao.updateDemand(demand);
        }
        else
        {
            demand.setCreateBy(ShiroUtils.getLoginName());
            // 新增product Demand信息
            count = demandDao.insertDemand(demand);
        }
        return count;
    }
}
