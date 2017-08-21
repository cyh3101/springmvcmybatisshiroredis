package com.cyh.core.shiro;

import com.cyh.common.model.UUser;
import com.cyh.core.CustomCachManager;
import com.cyh.core.shiro.session.SessionStatus;
import com.cyh.core.shiro.session.ShiroSessionRepository;
import com.cyh.user.bo.UserOnlineBo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by cai on 2017/8/20.
 */
public class CustomSessionManager {

    public static final String SESSION_STATUS = "session_online_status";
    ShiroSessionRepository shiroSessionRepository;
    CustomShiroSessionDAO customShiroSessionDAO;

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository){
        this.shiroSessionRepository = shiroSessionRepository;
    }

    public void setCustomShiroSessionDAO(CustomShiroSessionDAO customShiroSessionDAO){
        this.customShiroSessionDAO = customShiroSessionDAO;
    }

    /**
     * 得到所有的有效session用户
     * @return
     */
    public List<UserOnlineBo> getAllUser(){
        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
        List<UserOnlineBo> list = new ArrayList<>();
        if(sessions != null){
            for (Session session:sessions
                    ) {
                UserOnlineBo bo = getSessionBo(session);
                if(null != bo){
                    list.add(bo);
                }
            }
        }

        return list;
    }

    public UserOnlineBo getSessionBo(Session session){
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if(null == obj){
            return null;
        }
        if(obj instanceof SimplePrincipalCollection){
            SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
            //获取realm中doGetAuthenticationInfo方法返回的
            // SimpleAuthenticationInfo(user , user.getPswd() , getName())中的user对象
            obj = spc.getPrimaryPrincipal();
            if(obj != null && obj instanceof UUser){
                UserOnlineBo userBo = new UserOnlineBo((UUser) obj);
                userBo.setCreateTime(session.getStartTimestamp());
                userBo.setLastAccessTime(session.getLastAccessTime());
                userBo.setHost(session.getHost());
                userBo.setTimeout(session.getTimeout());
                SessionStatus onlineStatus = (SessionStatus)session.getAttribute(SESSION_STATUS);
                boolean status = Boolean.TRUE;
                if(onlineStatus != null) {
                    status = onlineStatus.getOnlineStatus();
                }
                userBo.setSessionStatus(status);
                return userBo;
            }
        }
        return null;
    }
}
