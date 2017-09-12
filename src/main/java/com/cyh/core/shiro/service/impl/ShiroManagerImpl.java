package com.cyh.core.shiro.service.impl;

import com.cyh.common.utils.SpringContextUtil;
import com.cyh.core.shiro.service.ShiroManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cai on 2017/9/12.
 */
public class ShiroManagerImpl implements ShiroManager{

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }

    @Override
    public String loadFilterChainDefinitions() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFixedAuthRule());
        return null;
    }

    private String getFixedAuthRule(){
        return null;
    }

    @Override
    public void reloadFilterChains() {

    }
}
