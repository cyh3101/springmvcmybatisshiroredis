package com.cyh.common.dao;

import com.cyh.common.bo.UPermissionBo;
import com.cyh.common.model.UPermission;
import com.cyh.common.model.UPermissionExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface UPermissionMapper {
    long countByExample(UPermissionExample example);

    int deleteByExample(UPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    List<UPermission> selectByExample(UPermissionExample example);

    UPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByExample(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

    List<UPermissionBo> selectPermissionById(Long id);

    Set<String> findPermissionByUserId(Long id);
}