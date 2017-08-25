package com.cyh.permission.service.impl;

import com.cyh.common.dao.UPermissionMapper;
import com.cyh.common.model.UPermission;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
@Service
public class PermissionServiceImpl extends BaseMybatisDao<UPermissionMapper> implements PermissionService {
    @Autowired
    private UPermissionMapper uPermissionMapper;

    @Override
    public Set<String> findPermissionByUserId(Long id) {
        return null;
    }

    @Override
    public Pagination<UPermission> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }
}
