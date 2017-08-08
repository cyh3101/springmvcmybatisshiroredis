package com.cyh.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cyh on 2017/8/8.
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param key cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期，以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String key,
                                 String value, int maxAge){
        try {
            Cookie cookie = new Cookie(key, value);
            if(maxAge > 0){
                cookie.setMaxAge(maxAge);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            LoggerUtils.error(CookieUtil.class, "创建cookies发生异常", e);
        }
    }

    /**
     * 清空cookie
     * @param request
     * @param response
     * @param name
     * @return
     */
    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response,
                                   String name){
        boolean bool = false;
        Cookie[] cookies = request.getCookies();
        if(null == cookies && cookies.length == 0){
            return bool;
        }

        try {
            for (int i = 0; i < cookies.length; i++){
                Cookie cookie = new Cookie(name, null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                bool = true;
            }
        }catch (Exception e){
            LoggerUtils.error(CookieUtil.class, "清空cookies异常",e);
        }
        return bool;
    }

    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response,
                                      String name, String domain){
        boolean bool = false;
        Cookie[] cookies = request.getCookies();
        if(null == cookies && cookies.length == 0){
            return bool;
        }

        try {
            for (int i = 0;i < cookies.length; i++){
                Cookie cookie = new Cookie(name, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setDomain(domain);
                response.addCookie(cookie);
            }
        }catch (Exception e){
            LoggerUtils.error(CookieUtil.class, "清除cookie错误", e);
        }
        return bool;
    }

    /**
     * 根据cookie名称得到cookie值
     * @param request
     * @param name
     * @return
     */
    public static String findCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(null == cookies && cookies.length == 0)
            return null;
        String cvalue = null;
        try {
            for (int i = 0; i < cookies.length; i++){
                Cookie cookie = cookies[i];
                String cname = cookie.getName();
                if(!StringUtils.isBlank(cname) && cname.equals(name)){
                    cvalue = cookie.getValue();
                }
            }
        }catch (Exception e){
            LoggerUtils.error(CookieUtil.class, "获取cookie发生异常", e);
        }

        return cvalue;
    }
}
