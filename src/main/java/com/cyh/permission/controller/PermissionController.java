package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UPermission;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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
    public ModelAndView index(ModelMap map, String findContent, Integer pageNo){
        map.put("findContent", findContent);
        Pagination<UPermission> page = permissionService.findPage(map, pageNo, pageSize);
        return new ModelAndView("permission/index", "page", page);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(String ids){
        return permissionService.deleteById(ids);
    }

    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPermission(UPermission permission){
        try {
            UPermission entity = permissionService.addPermission(permission);
            resultMap.put("status", 200);
            resultMap.put("message", "添加成功");
        } catch (Exception e){
            resultMap.put("status", 500);
            resultMap.put("message","添加失败");
        }
        return resultMap;
    }
}
