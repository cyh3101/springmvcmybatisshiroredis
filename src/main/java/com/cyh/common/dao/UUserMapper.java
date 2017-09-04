package com.cyh.common.dao;

import com.cyh.common.model.URole;
import com.cyh.common.model.UUser;
import com.cyh.common.model.UUserExample;
import java.util.List;
import java.util.Map;

import com.cyh.permission.bo.UserRoleBo;
import org.apache.ibatis.annotations.Param;

public interface UUserMapper {
    long countByExample(UUserExample example);

    int deleteByExample(UUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    List<UUser> selectByExample(UUserExample example);

    UUser selectByPrimaryKey(Long id);

    UUser selectByUserName(String userName);

    UUser selectUserByEmail(String email);

    List<URole> selectRoleByUserId(Long id);

    UUser login(Map<String , Object> map);

    int updateByExampleSelective(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByExample(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    UUser findAll();

    Integer findCount();

    List<UserRoleBo> findRoleById(Long id);

    int deleteRoleByIds(Map<String, Object> map);
}