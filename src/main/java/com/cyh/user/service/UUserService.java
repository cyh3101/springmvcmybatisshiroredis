package com.cyh.user.service;

import com.cyh.common.model.UUser;
import com.cyh.core.mybatis.page.Pagination;

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

}
