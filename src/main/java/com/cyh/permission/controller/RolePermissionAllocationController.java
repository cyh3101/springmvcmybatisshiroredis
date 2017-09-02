package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.bo.RolePermissionAllocationBo;
import com.cyh.permission.bo.RolePermissionBo;
import com.cyh.permission.service.PermissionService;
import com.cyh.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cai on 2017/8/31.
 */
@Controller
@RequestMapping(value = "/permission")
public class RolePermissionAllocationController extends BaseController{
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/allocation", method = RequestMethod.GET)
    public ModelAndView allocation(ModelMap map, Integer pageNo, String findContent){
        map.put("findContent", findContent);
        Pagination<RolePermissionAllocationBo> page = roleService.findRoleAndPermissionPage(map, pageNo, pageSize);
        return new ModelAndView("/permission/allocation", "page", page);
    }

    @RequestMapping(value = "/selectPermissionById", method = RequestMethod.POST)
    @ResponseBody
    public List<RolePermissionBo> selectPermissionById(Long id){
        return roleService.findPermissionById(id);
    }

    @RequestMapping(value = "/addPermission2Role", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPermission2Role(String ids, Long roleId){
        return permissionService.addPermission2Role(ids, roleId);
    }
}

