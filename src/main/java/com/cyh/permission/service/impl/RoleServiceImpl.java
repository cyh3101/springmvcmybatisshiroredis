package com.cyh.permission.service.impl;

import com.cyh.permission.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Override
    public Set<String> findRoleByUserId(Long id) {
        return null;
    }
}
