package com.feng.project.system.user.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.feng.project.system.user.domain.UserRole;

/**
 * 用户表 数据层
 * 
 * @author feng
 */
@Mapper
public interface IUserRoleDao
{

    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 通过角色ID删除用户和角色关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<UserRole> userRoleList);


    /**
     * get role id by user id
     * @param userId
     * @return
     */
    public int getRoleId(String userId);

}
