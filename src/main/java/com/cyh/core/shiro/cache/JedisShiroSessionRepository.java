package com.cyh.core.shiro.cache;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by cai on 2017/8/21.
 */
public class JedisShiroSessionRepository implements ShiroSessionRepository{
    private JedisManager jedisManager;
    public static final String REDIS_SHIRO_SESSION = "cyh-shiro-demo-session:";
    public static final String REDIS_SHIRO_ALL = "*cyh-shiro-demo-session:*";
    private static final int DB_INDEX = 1;

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    @Override
    public void saveSession(Session session) {
        System.out.println("JedisShiroSessionRepository.saveSession is run");
    }

    @Override
    public void deleteSession(Serializable sessionId) {

    }

    @Override
    public Session getSession(Serializable sessionId) {

        return null;
    }

    @Override
    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getJedisManager().getAllSession(DB_INDEX, REDIS_SHIRO_SESSION);
        } catch (Exception e){
            LoggerUtils.fmtError(getClass(), e, "获取全部session异常");
        }
        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId){
        return REDIS_SHIRO_SESSION + sessionId;
    }
}
