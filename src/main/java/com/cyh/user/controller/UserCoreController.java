package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UUser;
import com.cyh.common.utils.LoggerUtils;
import com.cyh.core.shiro.token.manager.TokenManager;
import com.cyh.user.manager.UserManager;
import com.cyh.user.service.UUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by cai on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/u")
public class UserCoreController extends BaseController{
    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView userIndex(){
        ModelAndView modelAndView = new ModelAndView("user/index");
        //modelAndView.addObject("token", TokenManager.getToken());
        return modelAndView;
    }

    @RequestMapping(value = "/updateSelf", method = RequestMethod.GET)
    public ModelAndView updateSelf(){
        ModelAndView modelAndView = new ModelAndView("user/updateSelf");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePswd", method = RequestMethod.GET)
    public ModelAndView updatePswd(){
        ModelAndView modelAndView = new ModelAndView("user/updatePswd");
        return modelAndView;
    }

    @RequestMapping(value = "/updateSelf",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateSelf(UUser user){
        try {
            uUserService.updateByPrimaryKeySelective(user);
            resultMap.put("status", 200);
            resultMap.put("message", "修改成功");
        }catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message", "修改失败");
            LoggerUtils.fmtError(getClass(), e, "修改个人资料出错。[%s]",
                    JSONObject.fromObject(user).toString());
        }
        return resultMap;
    }

    @RequestMapping(value = "/updatePswd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatePswd(String pswd, String newPswd){
        String email = TokenManager.getToken().getEmail();
        pswd = UserManager.getMD5(pswd, email);
        UUser user = uUserService.login(email, pswd);
        if("admin".equals(email)){
            resultMap.put("status", 300);
            resultMap.put("message","管理员不能修改密码");
            return resultMap;
        }
        if(null == user){
            resultMap.put("status", 300);
            resultMap.put("message", "用户名/密码错误");
            return resultMap;
        }else {
            user.setPswd(newPswd);
            //md5加密密码
            user = UserManager.getMD5(user);
            //修改密码
            uUserService.updateByPrimaryKeySelective(user);
            resultMap.put("status", 200);
            resultMap.put("message", "密码修改成功");
            //重新登录一下
            TokenManager.login(user,Boolean.TRUE);
        }

        return resultMap;
    }
}
