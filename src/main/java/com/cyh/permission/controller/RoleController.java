package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.URole;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.RoleService;
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
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(String findContent, ModelMap map){
        map.put("findContent", findContent);
        Pagination<URole> page = roleService.findPage(map, pageNo, pageSize);
        return new ModelAndView("role/index", "page", page);
    }

}
