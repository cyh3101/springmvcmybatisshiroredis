package com.cyh.core.filter;

import com.cyh.core.shiro.CustomSessionManager;
import com.cyh.core.shiro.session.SessionStatus;
import net.sf.json.JSONObject;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 简单的权限验证筛选器，判断用户是否登录
 * Created by cyh on 2017/9/13.
 */
public class SimpleAuthFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        String url = httpRequest.getRequestURI();
        //首先判断访问的uri地址是否是以"/open/"开头，如果是的，直接返回true
        if(url.startsWith("/open/")){
            return Boolean.TRUE;
        }
        //得到实体
        Subject subject = getSubject(servletRequest, servletResponse);
        //通过实体得到会话
        Session session = subject.getSession();
        Map<String, Object>  resultMap = new HashMap<>();
        SessionStatus sessionStatus = (SessionStatus)session.getAttribute(CustomSessionManager.SESSION_STATUS);
        if(null != sessionStatus && sessionStatus.isOnlineStatus()){
            if(ShiroFilterUtils.isAjax(httpRequest)){
                resultMap.put("status", 300);
                resultMap.put("message", "您已经被踢出，请重新登录");
                ShiroFilterUtils.out(servletResponse, resultMap);
                //out(servletRequest, servletResponse, resultMap);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //先进行退出
        Subject subject = getSubject(servletRequest, servletResponse);
        subject.logout();

        /**
         * 先保存要访问的地址
         * 比如：需要访问role/index，这个地址需要登录以后才能访问，登录以后要跳转到这个地址，先保存这个地址，
         * WebUtils.saveRequst(request);然后登录以后利用WebUtils.getSavedRequest(requst);得到登录以前需要访问的地址
         */
        WebUtils.saveRequest(servletRequest);
        WebUtils.issueRedirect(servletRequest, servletResponse, "/u/login");
        return Boolean.FALSE;
    }

//    private void out(ServletRequest request, ServletResponse response,Map<String, Object> resultMap) throws IOException {
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println(JSONObject.fromObject(resultMap).toString());
//        out.flush();
//        out.close();
//    }
}
