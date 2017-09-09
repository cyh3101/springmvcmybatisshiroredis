package com.cyh.core.shiro.cache;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.SerializeUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * Created by cai on 2017/9/6.
 */
public class JedisShiroCache<K,V> implements Cache<K,V>{

    private static final String REDIS_SHIRO_DEMO = "REDIS_SHIRO_DEMO:";
    private String name;

    private JedisManager jedisManager;

    static final Class<JedisShiroCache> SELF = JedisShiroCache.class;
    static final int REDIS_DB_INDEX = 1;

    public String getName() {
        if(null == name)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JedisShiroCache(String name, JedisManager jedisManager){
        this.name = name;
        this.jedisManager = jedisManager;
    }

    @Override
    public V get(K k) throws CacheException {
        byte[] bytekey = SerializeUtil.serialize(buildCacheKey(k));
        byte[] bytevalue = new byte[0];
        try {
            bytevalue = jedisManager.getValueByKey(REDIS_DB_INDEX, bytekey);
        }catch (Exception e){
            LoggerUtils.fmtError(SELF, "get value by key failed throw exception", e);
        }

        return (V)SerializeUtil.deserialize(bytevalue);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        V pre = get(k);
        byte[] bytekey = SerializeUtil.serialize(buildCacheKey(k));
        byte[] bytevalue = SerializeUtil.serialize(v);
        try {
            jedisManager.saveValueByKey(REDIS_DB_INDEX, bytekey, bytevalue, -1);
        }catch (Exception e){
            LoggerUtils.fmtError(SELF, "put value by key failed throw exception", e);
        }
        return pre;
    }

    @Override
    public V remove(K k) throws CacheException {
        V pre = get(k);
        byte[] bytekey = SerializeUtil.serialize(buildCacheKey(k));
        try {
            jedisManager.deleteValueByKey(REDIS_DB_INDEX, bytekey);
        }catch (Exception e){
            LoggerUtils.fmtError(SELF, "delete value by key failed throw exception", e);
        }
        return pre;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public String buildCacheKey(Object key){
        return REDIS_SHIRO_DEMO +  getName() + ":" + key;
    }
}
