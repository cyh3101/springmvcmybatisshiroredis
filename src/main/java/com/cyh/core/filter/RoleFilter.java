package com.cyh.core.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cyh on 2017/9/13.
 */
public class RoleFilter extends AccessControlFilter{
    private final static String LOGIN_URL = "/u/login";
    private final static String UNAUTHC_URL = "/open/unauthorized";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] roles = (String[])o;
        for (String role:roles
             ) {
            if(subject.hasRole(role)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if(null == subject.getPrincipal()){//表示没有登录
            WebUtils.saveRequest(servletRequest);//保存访问的uri
            WebUtils.issueRedirect(servletRequest, servletResponse, LOGIN_URL);
        }else {
            if(StringUtils.hasText(UNAUTHC_URL)){//如果有未授权的地址
                WebUtils.issueRedirect(servletRequest, servletResponse, UNAUTHC_URL);
            }else {//否知返回401未授权代码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
    }
}
