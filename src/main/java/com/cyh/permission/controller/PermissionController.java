package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UPermission;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by cai on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends BaseController{
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> map, String findContent){
        map.put("findContent", findContent);
        Pagination<UPermission> page = permissionService.findPage(map, pageNo, pageSize);
        return new ModelAndView("permission/index", "page", page);
    }

    @RequestMapping(value = "/allocation", method = RequestMethod.GET)
    public ModelAndView allocation(){
        return null;
    }
}
