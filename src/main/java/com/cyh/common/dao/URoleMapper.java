package com.cyh.common.dao;

import com.cyh.common.model.URole;
import com.cyh.common.model.URoleExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface URoleMapper {
    long countByExample(URoleExample example);

    int deleteByExample(URoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    List<URole> selectByExample(URoleExample example);

    URole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByExample(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

    Set<String> findRoleByUserId(Long id);

    List<URole> findNowAllPermission(Map<String , Object> map);

    void initData();

    List<URole> findAll();

    int findCount();
}