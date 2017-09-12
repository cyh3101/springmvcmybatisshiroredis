package com.cyh.core.filter;

import com.cyh.common.model.UUser;
import com.cyh.common.utils.LoggerUtils;
import com.cyh.core.shiro.token.manager.TokenManager;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyh on 2017/9/12.
 */
public class LoginFilter extends AccessControlFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        UUser user = TokenManager.getToken();
        if(null != user || isLoginRequest(servletRequest, servletResponse)){
            return Boolean.TRUE;
        }
        if(ShiroFilterUtils.isAjax(servletRequest)){
            Map<String, Object> resultMap = new HashMap<>();
            LoggerUtils.fmtDebug(getClass(), "当前用户没有登录");
            resultMap.put("login_status", 300);
                resultMap.put("message","\\u5F53\\u524D\\u7528\\u6237\\u6CA1\\u6709\\u767B\\u5F55\\uFF01");//当前用户没有登录
            ShiroFilterUtils.out(servletResponse, resultMap);
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return Boolean.FALSE;
    }
}
