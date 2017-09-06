package com.cyh.core.shiro.cache.impl;

import com.cyh.core.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by cai on 2017/7/14.
 */
public class CustomCacheManager implements CacheManager{

    private ShiroCacheManager shiroCacheManager;

    public ShiroCacheManager getShiroCacheManager() {
        return shiroCacheManager;
    }

    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
        this.shiroCacheManager = shiroCacheManager;
    }

    public void destroy(){
        this.shiroCacheManager.destroy();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return getShiroCacheManager().getCache(s);
    }
}
