package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.UserRoleAllocationBo;
import com.cyh.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cyh on 2017/8/29.
 */
@Controller
@RequestMapping(value = "/role")
public class UserRoleAllocationController extends BaseController{
    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "/allocation", method = RequestMethod.GET)
    public ModelAndView allocation(ModelMap modelMap, String findContent){
        modelMap.put("findContent", findContent);
        Pagination<UserRoleAllocationBo> page = uUserService.findUserAndRole(modelMap, pageNo, pageSize);
        modelMap.put("page", page);
        return new ModelAndView("/role/allocation");
    }
}
