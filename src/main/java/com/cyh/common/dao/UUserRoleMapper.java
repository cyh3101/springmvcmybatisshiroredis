package com.cyh.common.dao;

import com.cyh.common.model.UUserRole;
import com.cyh.common.model.UUserRoleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UUserRoleMapper {
    long countByExample(UUserRoleExample example);

    int deleteByExample(UUserRoleExample example);

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    List<UUserRole> selectByExample(UUserRoleExample example);

    int updateByExampleSelective(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int updateByExample(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int deleteByUserId(Long id);

    int deleteRoleByUserIds(Map<String , Object> resultMap);

    List<Long> findUserIdByRoleId(Long id);
}