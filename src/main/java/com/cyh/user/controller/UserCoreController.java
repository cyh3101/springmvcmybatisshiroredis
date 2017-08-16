package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.core.shiro.token.manager.TokenManager;
import com.cyh.user.service.UUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
