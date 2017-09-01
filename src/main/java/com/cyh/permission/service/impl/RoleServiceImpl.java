package com.cyh.permission.service.impl;

import com.cyh.common.dao.URoleMapper;
import com.cyh.common.model.URole;
import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.StringUtils;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.core.shiro.token.manager.TokenManager;
import com.cyh.permission.bo.RolePermissionAllocationBo;
import com.cyh.permission.bo.RolePermissionBo;
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
    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        return super.findPage("findRoleAndPermission", "findCount", map, pageNo, pageSize);
    }

    @Override
    public List<URole> findNowAllPermissions() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        return uRoleMapper.findNowAllPermission(map);
    }

    @Override
    public List<RolePermissionBo> findPermissionById(Long id) {
        return uRoleMapper.findPermissionById(id);
    }

    @Override
    public int insert(URole record) {
        return uRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(URole record) {
        return uRoleMapper.insertSelective(record);
    }

    /**
     * 根据id删除用户角色
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return uRoleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据ids删除相应的用户角色
     * @param ids
     * @return
     */
    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        String resultMsg = "删除成功";
        int count = 0;
        try{
            String[] idArray = new String[]{};
            if(StringUtils.contains(ids, ',')){
                idArray = ids.split(",");
            }else {
                idArray = new String[]{ids};
            }
            c:for (String idx : idArray){
                Long id = new Long(idx);
                if(new Long(1).equals(id)){
                    resultMsg = "删除成功，但admin角色不能删除";
                    continue c;
                } else {
                    count += this.deleteByPrimaryKey(id);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "删除成功");
            resultMap.put("count", count);
        }catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败");
            LoggerUtils.fmtError(getClass(), e, "删除ids出现错误，ids[%s]", ids);
        }
        return resultMap;
    }
}
