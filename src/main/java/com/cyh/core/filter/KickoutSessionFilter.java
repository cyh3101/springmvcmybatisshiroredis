package com.cyh.core.filter;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.core.shiro.cache.JedisManager;
import com.cyh.core.shiro.cache.VCache;
import com.cyh.core.shiro.session.ShiroSessionRepository;
import com.cyh.core.shiro.token.manager.TokenManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cyh on 2017/9/13.
 */
public class KickoutSessionFilter extends AccessControlFilter{

    private static String kickOutUrl = "/open/kickout";

    private final static int ONE_HOURE_SECONDES = 3600;
    private final static String ONLINE_USER = KickoutSessionFilter.class.getCanonicalName() + "_online_user";

    private final static String KICKOUT_STATUS = KickoutSessionFilter.class.getCanonicalName() + "_kickout_status";

    static ShiroSessionRepository sessionRepository;//session仓库

    public static String getKickOutUrl() {
        return kickOutUrl;
    }

    public static void setKickOutUrl(String kickOutUrl) {
        KickoutSessionFilter.kickOutUrl = kickOutUrl;
    }

    public static void setSessionRepository(ShiroSessionRepository sessionRepository) {
        KickoutSessionFilter.sessionRepository = sessionRepository;
    }

    static VCache vCache;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServlet = (HttpServletRequest)servletRequest;
        Subject subject = getSubject(servletRequest, servletResponse);
        String url = httpServlet.getRequestURI();
        if(url.startsWith("/open/") || (!subject.isAuthenticated() && !subject.isRemembered())){
            return Boolean.TRUE;
        }

        /**
         * 判断是否已经踢出了
         * 1.如果是ajax登录，返回json数据
         * 2.如果不是，直接返回到登录界面
         */
        //获取session信息
        Session session = subject.getSession();
        Serializable sessionId = session.getId();//得到sessionId
        Boolean mark = (Boolean)session.getAttribute(KICKOUT_STATUS);
        if (null != mark && mark){//如果已经被踢出了
            Map<String, Object> resultMap = new HashMap<>();
            if(ShiroFilterUtils.isAjax(servletRequest)){
                LoggerUtils.debug(getClass(), "还没有登录，而且是ajax登录");
                resultMap.put("status", 300);
                resultMap.put("message", "你已经在其他地方登录了,请重新登录");
                ShiroFilterUtils.out(servletResponse, resultMap);
            }
            return Boolean.FALSE;
        }

        //从cache中获取用户和session 的map信息
        Map<Long, Serializable> infoMap = VCache.get(ONLINE_USER, LinkedHashMap.class);
        //如果infoMap为null，创建一个
        infoMap = infoMap == null? new LinkedHashMap<Long, Serializable>():infoMap;

        Long userId = TokenManager.getUserId();
        //1.如果包含当前session，并是同一个用户的，表示为同一个用户登录，跳过
        if(infoMap.containsKey(userId) && infoMap.containsValue(sessionId)){
            VCache.setx(ONLINE_USER, infoMap, ONE_HOURE_SECONDES);
            return Boolean.TRUE;
        }

        //2.如果包含相同的用户，但是session的值不同，需要处理
        if(infoMap.containsKey(userId) && !infoMap.containsValue(sessionId)){
            Serializable oldSessionId = infoMap.get(userId);//获取原来的sessionId值
            Session oldSession = sessionRepository.getSession(oldSessionId);
            if(null != oldSession){
                //标记session的状态为踢出状态
                oldSession.setAttribute(KICKOUT_STATUS,Boolean.TRUE);
                sessionRepository.saveSession(oldSession);
                LoggerUtils.fmtDebug(getClass(), "kickout user success, sessionId : [%s]", oldSessionId.toString());
            } else {
                sessionRepository.deleteSession(oldSessionId);
                infoMap.remove(userId);
                VCache.setx(ONLINE_USER, infoMap, ONE_HOURE_SECONDES);//重新设置用户id->sessionId的键值对
            }
            return Boolean.TRUE;
        }

        //3.如果两个都不包含,把新的userid->sessionId增加到infoMap中
        if(!infoMap.containsKey(userId) && !infoMap.containsValue(sessionId)){
            infoMap.put(userId, sessionId);
            VCache.setx(ONLINE_USER, infoMap, ONE_HOURE_SECONDES);//重新存储用户id->sessionId的键值对
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        subject.logout();

        WebUtils.saveRequest(servletRequest);
        WebUtils.issueRedirect(servletRequest, servletResponse, kickOutUrl);
        return Boolean.FALSE;
    }
}
