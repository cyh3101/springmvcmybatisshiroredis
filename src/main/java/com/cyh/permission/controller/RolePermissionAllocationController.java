package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.RolePermissionAllocationBo;
import com.cyh.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cai on 2017/8/31.
 */
@Controller
@RequestMapping(value = "/permission")
public class RolePermissionAllocationController extends BaseController{
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/allocation", method = RequestMethod.GET)
    public ModelAndView allocation(ModelMap map, Integer pageNo, String findContent){
        map.put("findContent", findContent);
        Pagination<RolePermissionAllocationBo> page = roleService.findRoleAndPermissionPage(map, pageNo, pageSize);
        return new ModelAndView("/permission/allocation", "page", page);
    }

    @RequestMapping(value = "/selectPermissionById", method = RequestMethod.POST)
    public Map<String, Object> selectPermissionById(Long id){
        Map<String, Object> resultMap = new HashMap<>();
        return resultMap;
    }
}
