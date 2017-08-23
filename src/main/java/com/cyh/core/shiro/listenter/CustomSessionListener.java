package com.cyh.core.shiro.listenter;

import com.cyh.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created by cyh on 2017/8/23.
 */
public class CustomSessionListener implements SessionListener{
    private ShiroSessionRepository shiroSessionRepository;

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

    @Override
    public void onStart(Session session) {
        System.out.println("session on start");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("session on stop");
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }
}
