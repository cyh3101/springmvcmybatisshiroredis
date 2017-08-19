package com.cyh.common.controller;

import com.cyh.common.utils.StringUtils;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by cai on 2017/7/8.
 */
public class BaseController {
    protected int pageNo = 1;
    public static int pageSize = 10;
    protected final static Logger logger = Logger.getLogger(BaseController.class);

    protected Map<String , Object> resultMap = new LinkedHashMap<>();
    public static String URL404 = "404.html";

    private final static String PARAM_PAGE_NO = "pageNo";

    protected String pageSizeName = "pageSize";

    public static void setValue2Request(HttpServletRequest request , String key , Object value){
        request.setAttribute(key,value);
    }

    public static HttpSession getSession(HttpServletRequest request){
        return  request.getSession();
    }

    public int getPageNo(){
        return this.pageNo;
    }

    public int getPageSize(){
        return BaseController.pageSize;
    }

    public void setPageNo(int pageNo){
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize){
        BaseController.pageSize = pageSize;
    }

    /**
     * 跳转到指定页
     * @param redirectUrl
     * @param params
     * @return
     */
    public ModelAndView redirect(String redirectUrl , Map<String , Object>...params){
        ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
        if(null != params){
            view.addAllObjects(params[0]);
        }
        return view;
    }

    /**
     * 跳转到404页面
     * @return
     */
    public ModelAndView redirect404(){
        ModelAndView view = new ModelAndView(new RedirectView(URL404));
        return view;
    }

    /**
     * 准备请求参数
     * @param obj
     * @param request
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    protected Map<String , Object> prepareParams(Object obj , HttpServletRequest request) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String pageNoStr = request.getParameter(PARAM_PAGE_NO);
        String pageSizeStr = request.getParameter(pageSizeName);
        if(null != pageNoStr){
            pageNo = Integer.getInteger(pageNoStr);
        }
        if(null != pageSizeStr){
            pageSize = Integer.getInteger(pageSizeStr);
        }

        Map<String , Object> params = new HashMap<String , Object>();
        params = BeanUtils.describe(obj);
        params = handleParams(params);
        return params;
    }

    /**
     * 处理请求参数
     * @param params
     * @return
     */
    public Map<String , Object> handleParams(Map<String , Object> params){
        Map<String , Object> resultMap = new HashMap<>();
        if(null != params){
            Set<Map.Entry<String , Object>> entrySet = params.entrySet();
            for (Iterator<Map.Entry<String , Object>> it = entrySet.iterator();it.hasNext();){
                Map.Entry<String , Object> entry = it.next();
                if(entry.getValue() != null){
                    resultMap.put(entry.getKey() , StringUtils.trimToEmpty((String)entry.getValue()));
                }
            }
        }
        return resultMap;
    }

}
