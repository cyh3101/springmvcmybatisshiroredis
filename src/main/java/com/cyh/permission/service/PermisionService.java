package com.cyh.permission.service;

import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public interface PermisionService {
    Set<String> findPermissionByUserId(Long id);
}
