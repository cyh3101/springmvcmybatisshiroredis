package com.cyh.core.shiro.cache;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.SerializeUtil;
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
    private static final int SESSION_VAL_TIME_SPAN = 18000;

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    @Override
    public void saveSession(Session session) {
        if(null == session || session.getId() == null){
            throw new NullPointerException("session is empty");
        }
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));

            byte[] value = SerializeUtil.serialize(session);
            long sessionTimeOut = session.getTimeout() / 1000;
            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
            getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());
            System.out.println("JedisShiroSessionRepository.saveSession is run");
        } catch (Exception e){
            LoggerUtils.fmtError(getClass(), e, "save session error id:[%s]", session.getId());
        }

    }

    @Override
    public void deleteSession(Serializable sessionId) {

    }

    @Override
    public Session getSession(Serializable sessionId) {
        if(null == sessionId){
            throw new NullPointerException("session id is null");
        }
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
            session = SerializeUtil.deserialize(value, Session.class);
        } catch (Exception e){
            LoggerUtils.fmtError(getClass(), e, "获取Session异常，id:[%s]", sessionId);
        }

        return session;
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
