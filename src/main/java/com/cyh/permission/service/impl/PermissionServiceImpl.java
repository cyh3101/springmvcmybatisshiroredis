package com.cyh.permission.service.impl;

import com.cyh.common.dao.RolePermissionMapper;
import com.cyh.common.dao.UPermissionMapper;
import com.cyh.common.model.RolePermission;
import com.cyh.common.model.UPermission;
import com.cyh.common.utils.StringUtils;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
@Service
public class PermissionServiceImpl extends BaseMybatisDao<UPermissionMapper> implements PermissionService {
    @Autowired
    private UPermissionMapper uPermissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> findPermissionByUserId(Long id) {
        return uPermissionMapper.findPermissionByUserId(id);
    }

    @Override
    public Pagination<UPermission> findPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage(map, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> addPermission2Role(String ids, Long roleId) {
        //先删除所有的权限
        rolePermissionMapper.deleteByRoleId(roleId);
        //再增加权限
        return executePermission(ids, roleId);
    }

    @Override
    public Map<String, Object> deleteById(String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        String[] idArray = new String[]{};
        int count = 0;
        if(StringUtils.contains(ids, ',')){
            idArray = ids.split(",");
        } else {
            idArray = new String[]{ids};
        }
        try {
            for (String id:idArray
                    ) {
                count += uPermissionMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("message", "删除成功");
        } catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败");
        }
        resultMap.put("count", count);
        return resultMap;
    }

    @Override
    public UPermission addPermision(UPermission permission) {
        uPermissionMapper.insert(permission);
        return permission;
    }

    public Map<String, Object> executePermission(String ids, Long roleId){
        Map<String, Object> resultMap = new HashMap<>();
        int count = 0;
        if(!StringUtils.isBlank(ids)){
            String[] pIds = null;
            if(ids.contains(",")){
                pIds = ids.split(",");
            } else {
                pIds = new String[]{ids};
            }
            try {
                for (String id:pIds
                        ) {
                    if(id != null && id != ""){
                        RolePermission entity = new RolePermission();
                        entity.setRid(roleId);
                        entity.setPid(new Long(id));
                        count += rolePermissionMapper.insertSelective(entity);
                    }
                }
                resultMap.put("status", 200);
                resultMap.put("message", "操作成功");
            }catch (Exception e){
                resultMap.put("status",500);
                resultMap.put("message","操作失败");
            }
        }

        resultMap.put("count", count);

        return resultMap;
    }
}
