package com.cyh.common.controller;

import com.cyh.common.utils.VerifyCodeUtils;
import com.cyh.core.shiro.token.manager.TokenManager;
import com.cyh.permission.service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cai on 2017/7/23.
 */
@Controller
@RequestMapping(value = "open")
public class CommonController extends BaseController{
    @Resource
    RoleService roleService;

    /**
     * 404错误
     * @param request
     * @return
     */
    @RequestMapping(value = "404")
    public ModelAndView _404(HttpServletRequest request){
        ModelAndView view = new ModelAndView("common/404");
        return view;
    }

    /**
     * 500错误
     * @param request
     * @return
     */
    @RequestMapping(value = "500")
    public ModelAndView _500(HttpServletRequest request){
        ModelAndView view = new ModelAndView("common/500");
        Throwable t = (Throwable)request.getAttribute("javax.servlet.error.Exception");
        String defaultMessage = "未知";
        if(null == t){
            view.addObject("line",defaultMessage);
            view.addObject("clazz",defaultMessage);
            view.addObject("methodName",defaultMessage);
            return view;
        }
        String message = t.getMessage();
        StackTraceElement[] stack = t.getStackTrace();
        view.addObject("message",message);
        if(null != stack && stack.length != 0){
            StackTraceElement element = stack[0];
            int line = element.getLineNumber();
            String clazz = element.getClassName();
            String fileName = element.getFileName();
            String methodName = element.getMethodName();
            view.addObject("line",line);
            view.addObject("clazz",clazz);
            view.addObject("methodName",methodName);
        }
        return view;
    }

    /**
     * 得到验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "getVCode" , method = RequestMethod.GET)
    public void getVCode(HttpServletRequest request , HttpServletResponse response){
        //防止输出被浏览器保存在缓存区中
        response.setHeader("Prama", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpg");

        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        TokenManager.setValue2Session(VerifyCodeUtils.V_CODE, verifyCode);

    }
}
