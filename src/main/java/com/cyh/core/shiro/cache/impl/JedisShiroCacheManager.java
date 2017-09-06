package com.cyh.core.shiro.cache.impl;

import com.cyh.core.shiro.cache.JedisManager;
import com.cyh.core.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

/**
 * Created by cai on 2017/7/14.
 */
public class JedisShiroCacheManager implements ShiroCacheManager{
    private JedisManager jedisManager = null;

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return null;
    }

    @Override
    public void destroy() {

    }
}
