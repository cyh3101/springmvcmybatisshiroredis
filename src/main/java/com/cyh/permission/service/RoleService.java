package com.cyh.permission.service;

import com.cyh.common.dao.URoleMapper;
import com.cyh.common.dao.UUserRoleMapper;
import com.cyh.common.model.URole;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;

import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public interface RoleService{
    Set<String> findRoleByUserId(Long id);

    Pagination<URole> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize);
}
