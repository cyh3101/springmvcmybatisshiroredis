package com.cyh.user.service.impl;

import com.cyh.common.dao.UUserMapper;
import com.cyh.common.dao.UUserRoleMapper;
import com.cyh.common.model.UUser;
import com.cyh.common.model.UUserRole;
import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.StringUtils;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.UserRoleAllocationBo;
import com.cyh.permission.bo.UserRoleBo;
import com.cyh.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cai on 2017/7/9.
 */
@Service
public class UUserServiceImpl extends BaseMybatisDao<UUserMapper> implements UUserService{
    @Autowired
    private UUserMapper userMapper;

    @Autowired
    private UUserRoleMapper userRoleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UUser insert(UUser record) {
        this.userMapper.insert(record);
        return record;
    }

    @Override
    public UUser insertSelective(UUser recored) {
        this.userMapper.insertSelective(recored);
        return recored;
    }

    @Override
    public UUser selectByPrimaryKey(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UUser record) {
        return this.userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UUser record) {
        return this.userMapper.updateByPrimaryKey(record);
    }

    @Override
    public UUser login(String email, String password) {
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("email" , email);
        map.put("pswd" , password);
        UUser user = this.userMapper.login(map);
        return user;
    }

    @Override
    public UUser findUserByEmail(String email) {
        return this.userMapper.selectUserByEmail(email);
    }

    @Override
    public Map<String, Object> addRole2User(Long userId, String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        int count = 0;
        try {
            //先删除所有权限
            userRoleMapper.deleteByUserId(userId);
            if(!StringUtils.isBlank(ids)){
                String[] idArray = null;
                if(StringUtils.contains(ids, ",")){
                    idArray = ids.split(",");
                }else {
                    idArray = new String[]{ids};
                }

                for (String id:idArray
                        ) {
                    if(!StringUtils.isBlank(id)){
                        UUserRole entity = new UUserRole();
                        entity.setUid(userId);
                        entity.setRid(new Long(id));
                        count += userRoleMapper.insertSelective(entity);
                    }
                }
            }
            resultMap.put("status", 200);
            resultMap.put("message", "操作成功");
        }catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "操作失败");
        }
        resultMap.put("count", count);

        return resultMap;
    }

    @Override
    public Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> forbidUserById(Long status, Long id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            UUser user = userMapper.selectByPrimaryKey(id);
            user.setStatus(status);
            userMapper.updateByPrimaryKey(user);
            resultMap.put("status",200);
            resultMap.put("message","成功更新状态");
        } catch (Exception e){
            resultMap.put("status",500);
            resultMap.put("message","操作失败，请重试");
            LoggerUtils.fmtError(getClass(), e, "改变用户状态失败,id:[%s],status:[%s]",id,status);
        }
        return resultMap;
    }

    @Override
    public Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
    }

    @Override
    public List<UserRoleBo> findRoleById(Long id) {
        return userMapper.findRoleById(id);
    }

    /**
     * 通过ids字符串删除用户
     * @param ids
     * @return
     */
    @Override
    public Map<String, Object> deleteById(String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        int count = 0;
        String[] idArray = new String[]{};
        try {
            if(ids.contains(",")){
                idArray = ids.split(",");
            }else {
                idArray = new String[]{ids};
            }
            for (String id:idArray
                    ) {
                count += userMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status",200);
            resultMap.put("message","删除成功");
        }catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败");
        }finally {
            resultMap.put("count", count);
        }
        return resultMap;
    }

    /**
     * 通过用户ids清空用户的角色信息，用户id以','隔开
     * @param ids
     * @return
     */
    @Override
    public Map<String, Object> clearRoleByUserIds(String ids) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("userIds", ids);
            userMapper.deleteRoleByIds(resultMap);
            resultMap.put("status", 200);
            resultMap.put("message", "删除成功");
        }catch (Exception e){
            resultMap.put("status",500);
            resultMap.put("message","删除失败");
        }
        return resultMap;
    }
}
