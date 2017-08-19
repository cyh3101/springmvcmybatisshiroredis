package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UUser;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cyh3101 on 2017/8/19.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController{
    //@Autowired
    //private UUserService uUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent){
        map.put("findContent",findContent);
        //Pagination<UUser> page = uUserService.findByPage(map, pageNo, pageSize);
        //map.put("page", page);
        return new ModelAndView("member/list");
    }

    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public ModelAndView online(){
        return new ModelAndView("member/online");
    }
}
