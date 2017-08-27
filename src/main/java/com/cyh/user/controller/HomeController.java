package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cai on 2017/7/8.
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController extends BaseController{
    public String home(){
        return "redirect:/u/login";
    }
}
