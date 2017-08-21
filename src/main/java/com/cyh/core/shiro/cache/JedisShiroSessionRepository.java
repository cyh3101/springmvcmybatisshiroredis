package com.cyh.core.shiro.cache;

import com.cyh.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by cai on 2017/8/21.
 */
public class JedisShiroSessionRepository implements ShiroSessionRepository{
    private JedisManager jedisManager;

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    @Override
    public void saveSession(Session session) {

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
        return null;
    }
}
