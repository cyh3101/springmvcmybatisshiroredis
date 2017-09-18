package com.cyh.core.shiro.cache;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.SerializeUtil;
import com.cyh.common.utils.SpringContextUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by cai on 2017/9/18.
 */
public class VCache {
    final static JedisManager jedisMananger = SpringContextUtil.getBean("jedisManager", JedisManager.class);

    private VCache(){}

    /**
     * 通过key值得到
     * @param key
     * @param requiredClass
     * @param <T>
     * @return
     */
    public static <T> T get(String key, Class<T>...requiredClass){
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = jedisMananger.getJedis();
            jedis.select(0);
            byte[] byteKey = SerializeUtil.serialize(key);
            byte[] byteValue = jedis.get(byteKey);
            return SerializeUtil.deserialize(byteValue,requiredClass);
        }catch (Exception e){
            isBroken = true;
            e.printStackTrace();
        }finally {
            returnResource(jedis, isBroken);
        }
        return null;
    }

    /**
     * 存储值到jedis
     * @param key key
     * @param val 值
     */
    public static void set(String key, Object val){
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = jedisMananger.getJedis();
            byte[] byteKey = SerializeUtil.serialize(key);
            byte[] byteValue = SerializeUtil.serialize(val);
            jedis.select(0);
            jedis.set(byteKey, byteValue);
        }catch (Exception e){
            isBroken = true;
            e.printStackTrace();
        } finally {
            returnResource(jedis,isBroken);
        }
    }

    /**
     * 保存键值对到jedis，并设置过期时间
     * @param key key
     * @param val 值
     * @param timer 过期时间
     */
    public static void setx(String key, Object val, int timer){
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = jedisMananger.getJedis();
            byte[] byteKey = SerializeUtil.serialize(key);
            byte[] byteValue = SerializeUtil.serialize(val);
            jedis.select(0);
            jedis.setex(byteKey, timer, byteValue);
        } catch (Exception e){
            isBroken = true;
            e.printStackTrace();
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    /**
     * 归还资源
     * @param jedis
     * @param isBroken
     */
    public static void returnResource(Jedis jedis, boolean isBroken){
        if(null == jedis){
            return;
        }
        if(isBroken){
            jedisMananger.getJedisPool().returnBrokenResource(jedis);
        }else {
            jedisMananger.getJedisPool().returnResource(jedis);
        }
    }
}
