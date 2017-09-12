package com.cyh.core.shiro.service;

/**
 * Created by cai on 2017/9/12.
 */
public interface ShiroManager {
    /**
     * 加载过滤配置信息
     * @return
     */
    String loadFilterChainDefinitions();

    /**
     * 重新加载过滤配置信息，一般是在用户角色、配置信息修改了以后，重新加载过滤信息
     */
    void reloadFilterChains();
}
