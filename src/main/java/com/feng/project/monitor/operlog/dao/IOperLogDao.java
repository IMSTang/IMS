package com.feng.project.monitor.operlog.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.feng.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志 数据层
 * 
 * @author feng
 */
@Mapper
public interface IOperLogDao
{
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<OperLog> selectOperLogList(OperLog operLog);
    
    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int batchDeleteOperLog(Long[] ids);
    
    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public OperLog selectOperLogById(Long operId);
}
