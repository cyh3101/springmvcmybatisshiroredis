package com.cyh.core.filter;

import com.cyh.common.utils.LoggerUtils;
import net.sf.json.JSONObject;
import org.springframework.http.HttpRequest;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by cyh on 2017/9/12.
 */
public class ShiroFilterUtils {
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"));
    }

    public static void out(ServletResponse response, Map<String, Object> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSONObject.fromObject(resultMap));
        }catch (Exception e){
            LoggerUtils.fmtError(ShiroFilterUtils.class, "输出失败" ,e);
        }finally {
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
