package com.cyh.user.service;

import com.cyh.common.model.UUser;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.UserRoleAllocationBo;
import com.cyh.permission.bo.UserRoleBo;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by cai on 2017/7/6.
 */
public interface UUserService {
    int deleteByPrimaryKey(Long id);

    UUser insert(UUser record);

    UUser insertSelective(UUser recored);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    UUser login(String email , String password);

    UUser findUserByEmail(String email);

    Map<String , Object> addRole2User(Long userId , String ids);

    Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    Map<String, Object> forbidUserById(Long status, Long id);

    Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap, Integer pageNo,
                                                     Integer pageSize);

    List<UserRoleBo> findRoleById(Long id);

    Map<String, Object> deleteById(String ids);
}
