package com.cyh.permission.service;

import com.cyh.common.dao.URoleMapper;
import com.cyh.common.dao.UUserRoleMapper;
import com.cyh.common.model.URole;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.RolePermissionAllocationBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public interface RoleService {
    Set<String> findRoleByUserId(Long id);

    Pagination<URole> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    List<URole> findNowAllPermissions();

    int insert(URole record);

    int insertSelective(URole record);

    int deleteByPrimaryKey(Long id);

    Map<String, Object> deleteRoleById(String ids);
}