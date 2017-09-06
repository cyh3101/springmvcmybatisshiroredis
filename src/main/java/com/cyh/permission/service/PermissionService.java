package com.cyh.permission.service;

import com.cyh.common.model.UPermission;
import com.cyh.core.mybatis.page.Pagination;

import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public interface PermissionService {

    Set<String> findPermissionByUserId(Long id);

    Pagination<UPermission> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize);

    Map<String, Object> addPermission2Role(String ids, Long roleId);

    Map<String, Object> deleteById(String ids);

    UPermission addPermission(UPermission permission);
}
