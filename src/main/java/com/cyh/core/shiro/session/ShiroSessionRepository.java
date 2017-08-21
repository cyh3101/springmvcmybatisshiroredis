package com.cyh.core.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by cyh on 2017/8/21.
 */
public interface ShiroSessionRepository {

    /**
     * 存储session
     * @param session
     */
    void saveSession(Session session);

    /**
     * 删除session
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);

    /**
     * 得到session
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);

    /**
     * 得到所有session
     * @return
     */
    Collection<Session> getAllSessions();
}
