package com.cyh.core.shiro.token.manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by cyh on 2017/7/25.
 */
public class TokenManager {

    /**
     * 获取当前用户的session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 把值放入当前登录的用户中
     * @param key
     * @param value
     */
    public static void setValue2Session(Object key , Object value){
        getSession().setAttribute(key, value);
    }

    /**
     * 从当前登录用户取值
     * @param key
     * @return
     */
    public static Object getValue2Session(Object key){
        return getSession().getAttribute(key);
    }
}
