package com.cyh.core.shiro.cache;

import com.cyh.common.utils.SerializeUtil;
import com.cyh.common.utils.StringUtils;
import org.apache.shiro.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cai on 2017/8/21.
 */
public class JedisManager {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 归还jedis资源
     * @param jedis
     * @param isBroken
     */
    public void returnResource(Jedis jedis, boolean isBroken){
        if(null == jedis){
            return;
        }
        if(isBroken){
            getJedisPool().returnBrokenResource(jedis);
        } else {
            getJedisPool().returnResource(jedis);
        }
    }
    /**
     * 得到jedis对象
     * @return
     */
    public Jedis getJedis(){
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (JedisConnectionException e){
            String message = StringUtils.trim(e.getMessage());
            if("Could not get a resource from the pool".equalsIgnoreCase(message)){
                System.out.println("+++++++++++请检查你的redis服务++++++++++++");
                System.out.println("1.请检查是否安装redis服务");
                System.out.println("2.请检查redis服务是否开启");
                System.exit(0);//停止项目
            }
            throw new JedisConnectionException(e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return jedis;
    }

    /**
     * 存入值
     * @param dbIndex
     * @param key
     * @param value
     * @param expireTime
     */
    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime){
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if(expireTime > 0){
                jedis.expire(key, expireTime);
            }
        } catch (Exception e){
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    /**
     * 通过key从redis中得到相关的值
     * @param dbIndex
     * @param key
     * @return
     */
    public byte[] getValueByKey(int dbIndex, byte[] key){
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e){
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    /**
     * 得到所有的sessions
     * @param dbIndex
     * @param redisShiroSession
     * @return
     */
    public Collection<Session> getAllSession(int dbIndex, String redisShiroSession){
        Jedis jedis = null;
        boolean isBroken = false;
        Set<Session> sessions = new HashSet<>();
        try {
            jedis = getJedis();
            jedis.select(dbIndex);

            Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());
            if(byteKeys != null && byteKeys.size() > 0){
                for (byte[] bs:byteKeys
                     ) {
                    Session obj = SerializeUtil.deserialize(jedis.get(bs), Session.class);
                    if(obj instanceof Session){
                        sessions.add(obj);
                    }
                }
            }
        } catch (Exception e){
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis,isBroken);
        }
        return sessions;
    }
}
