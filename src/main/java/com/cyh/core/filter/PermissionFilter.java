package com.cyh.core.filter;

import com.cyh.common.utils.LoggerUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限过滤器
 * Created by cyh on 2017/9/13.
 */
public class PermissionFilter extends AccessControlFilter{
    /**
     * 判断是否允许访问，如果返回true表示允许访问
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if(null != o){
            String[] pers = (String[])o;
            for (String per:pers
                 ) {
                if(subject.isPermitted(per)){//如果实体有相应的权限，返回true
                    return Boolean.TRUE;
                }
            }
        }
        HttpServletRequest httpServlet = (HttpServletRequest)servletRequest;
        String uri = httpServlet.getRequestURI();//获取uri
        String basePath = httpServlet.getContextPath();//获取basePath
        if(basePath.contains(uri)){
            uri = uri.replaceFirst(basePath, "");
        }
        if(subject.isPermitted(uri)){
            return Boolean.TRUE;
        }
        if(ShiroFilterUtils.isAjax(servletRequest)){//如果是ajax请求
            Map<String, Object> resultMap = new HashMap<>();
            LoggerUtils.debug(getClass(), "当前用户没有登录，并且是ajax登录的");
            resultMap.put("login_status", 300);
            resultMap.put("message", "\\u5F53\\u524D\\u7528\\u6237\\u6CA1\\u6709\\u767B\\u5F55\\uFF01");//当前用户没有登录，并且是ajax登录的
            ShiroFilterUtils.out(servletResponse,resultMap);
        }
        return Boolean.FALSE;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理，继续拦截器链处理；返回false表示需要自己处理
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject =getSubject(servletRequest, servletResponse);
        if(null == subject.getPrincipal()){
            WebUtils.saveRequest(servletRequest);
            WebUtils.issueRedirect(servletRequest, servletResponse, ShiroFilterUtils.LOGIN_URL);
        }else {
            if(StringUtils.hasText(ShiroFilterUtils.UNAUTHC_URL)){//查看是否有未授权的页面，如果有就跳转到改页面
                WebUtils.issueRedirect(servletRequest, servletResponse,ShiroFilterUtils.UNAUTHC_URL);
            }else {//如果没有就返回401错误代码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
    }
}
