package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UUser;
import com.cyh.common.utils.StringUtils;
import com.cyh.common.utils.VerifyCodeUtils;
import com.cyh.user.manager.UserManager;
import com.cyh.user.service.UUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by cai on 2017/7/8.
 */
@Controller
@RequestMapping(value = "/u")
public class UserLoginController extends BaseController{

    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("user/register");
    }

    //登录提交
    @RequestMapping(value = "/submitLogin" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> submitLogin(UUser user , boolean rememberMe , HttpServletRequest request){
        Map<String , Object> resultMap = new HashMap<String , Object>();
        try {
            user = UserManager.getMD5(user);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail() , user.getPswd());

            token.setRememberMe(rememberMe);
            System.out.println("user : " + user.toString());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            resultMap.put("status" , 200);
            resultMap.put("message" , "登录成功");

            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = null;
            if(null != savedRequest){
                url = savedRequest.getRequestUrl();
            }
            if(StringUtils.isBlank(url)){
                url = request.getContextPath() + "/u/index";
            }
            resultMap.put("back_url", url);
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

    @RequestMapping(value = "/subRegister" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> subRegister(String vcode, UUser user){
        resultMap.put("status", 400);
        if(!VerifyCodeUtils.verifyCode(vcode)){
            resultMap.put("message","验证码不正确");
            return resultMap;
        }
        String email = user.getEmail();
        if(null != uUserService.findUserByEmail(email)){
            resultMap.put("status" , 500);
            resultMap.put("message" , "用户已经注册过了");
            return resultMap;
        }
        Date date = new Date();
        user.setCreateTime(date);
        user.setLastLoginTime(date);
        user = UserManager.getMD5(user);
        user.setStatus(new Long(1));
        uUserService.insert(user);

        System.out.println("注册成功");
        resultMap.put("status" , 200);
        resultMap.put("message" , "注册成功");

        return resultMap;

        //home branch



    }
}
