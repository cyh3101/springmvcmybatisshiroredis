package com.cyh.permission.service;

import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public interface RoleService {
    Set<String> findRoleByUserId(Long id);
}
