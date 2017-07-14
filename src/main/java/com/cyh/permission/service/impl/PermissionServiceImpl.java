package com.cyh.permission.service.impl;

import com.cyh.common.dao.UPermissionMapper;
import com.cyh.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private UPermissionMapper uPermissionMapper;

    @Override
    public Set<String> findPermissionByUserId(Long id) {
        return null;
    }
}
