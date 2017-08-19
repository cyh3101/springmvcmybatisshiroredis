package com.cyh.user.service.impl;

import com.cyh.common.dao.UUserMapper;
import com.cyh.common.model.UUser;
import com.cyh.core.mybatis.page.BaseMybatisDao;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cai on 2017/7/9.
 */
@Service
public class UUserServiceImpl implements UUserService{
    @Autowired
    private UUserMapper userMapper;
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
        return null;
    }

    @Override
    public Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        //return super.findPage(resultMap, pageNo, pageSize);
        return null;
    }
}
