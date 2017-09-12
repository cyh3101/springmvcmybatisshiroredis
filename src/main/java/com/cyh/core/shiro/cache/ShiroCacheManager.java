package com.cyh.core.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * Created by cai on 2017/7/14.
 */
public interface ShiroCacheManager{
   <K , V> Cache<K , V> getCache(String name);
   void destroy();
}
