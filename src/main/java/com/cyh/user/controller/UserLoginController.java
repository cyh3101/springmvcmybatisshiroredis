package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UUser;
import com.cyh.user.service.UUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cai on 2017/7/8.
 */
@RequestMapping(value = "u")
public class UserLoginController extends BaseController{

    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "register" , method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("user/register");
    }

    //登录提交
    @RequestMapping(value = "submitLogin" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> submitLogin(UUser user , boolean rememberMe , HttpServletRequest request){
        Map<String , Object> resultMap = new HashMap<String , Object>();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail() , user.getPswd());
            token.setRememberMe(rememberMe);

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            resultMap.put("status" , 200);
            resultMap.put("message" , "登录成功");
        } catch (UnknownAccountException e){
            resultMap.put("status" , 500);
            resultMap.put("message" , "账号或密码错误");
        } catch (DisabledAccountException e){
            resultMap.put("status" , 500);
            resultMap.put("message" , "账号已经禁用");
        } catch (Exception e){
            resultMap.put("status" , 500);
            resultMap.put("message" , "账号或密码错误");
        }
        return  resultMap;
    }
}
