package com.cyh.permission.service.impl;

import com.cyh.common.dao.URoleMapper;
import com.cyh.common.model.URole;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.core.shiro.token.manager.TokenManager;
import com.cyh.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
@Service
public class RoleServiceImpl extends BaseMybatisDao<URoleMapper> implements RoleService{
    @Autowired
    private URoleMapper uRoleMapper;

    @Override
    public Set<String> findRoleByUserId(Long id) {
        return null;
    }

    @Override
    public Pagination<URole> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }

    @Override
    public List<URole> findNowAllPermissions() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        return uRoleMapper.findNowAllPermission(map);
    }
}
