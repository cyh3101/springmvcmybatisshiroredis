package com.cyh.core.shiro.token.manager;

import com.cyh.common.model.UUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by cyh on 2017/7/25.
 */
public class TokenManager {

    /**
     * 获取当前登录用户的token
     * @return
     */
    public static UUser getToken(){
        return (UUser)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 得到当前登录用户的名字
     * @return
     */
    public static String getNickName(){
        return getToken().getNickname();
    }

    /**
     * 得到当前登录用户的id
     * @return
     */
    public static Long getUserId(){
        return getToken() == null? null : getToken().getId();
    }

    /**
     * 用户登录
     * @param user
     * @param isRemember
     * @return
     */
    public static UUser login(UUser user , boolean isRemember){
        ShiroToken token = new ShiroToken(user.getEmail(), user.getPswd());
        token.setRememberMe(isRemember);
        SecurityUtils.getSubject().login(token);
        return getToken();
    }

    /**
     * 判断是否已经登录
     * @return
     */
    public static boolean isLogin(){
        return null != SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 登出
     */
    public static void logout(){
        SecurityUtils.getSubject().logout();
    }
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
