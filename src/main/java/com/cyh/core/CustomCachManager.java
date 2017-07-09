package com.cyh.core;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by cai on 2017/7/9.
 */
public class CustomCachManager implements CacheManager{
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return null;
    }
}
