package com.cyh.core.shiro.cache;

import com.cyh.common.utils.LoggerUtils;
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
 * jedis管理器,通过该类可以从jedis池中得到jedis实例资源
 * 通过jedis实例资源保存数据到redis服务器/通过key从redis服务器中得到相关的数据
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
     * 通过key从redis中删除相关的数据
     * @param dbIndex
     * @param key
     */
    public void deleteValueByKey(int dbIndex, byte[] key){
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
            LoggerUtils.fmtDebug(getClass(),"删除成功:%s", result.toString());
        } catch (Exception e){
            isBroken = true;
            throw e;
        }finally {
            returnResource(jedis, isBroken);
        }
    }

    /**
     * 判断是否存在key
     * @param key
     * @return
     */
    public boolean isExists(String key){
        return isExists(SerializeUtil.serialize(key));
    }

    public boolean isExists(byte[] key){
        Jedis jedis = null;
        boolean isBroken = false;
        boolean isExist = false;
        try {
            jedis = getJedis();
            isExist = jedis.exists(key);
        }catch (Exception e){
            isBroken = true;
        }finally {
            returnResource(jedis, isBroken);
        }
        return isExist;
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
