package com.cyh.permission.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.URole;
import com.cyh.common.utils.LoggerUtils;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.permission.service.RoleService;
import com.cyh.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cyh3101 on 2017/8/19.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
    @Autowired
    RoleService roleService;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(String findContent, ModelMap map){
        map.put("findContent", findContent);
        Pagination<URole> page = roleService.findPage(map, pageNo, pageSize);
        return new ModelAndView("role/index", "page", page);
    }

    @RequestMapping(value = "/myPermission", method = RequestMethod.GET)
    public ModelAndView myPermission(){
        return new ModelAndView("permission/myPermission");
    }

    @RequestMapping(value = "/getMyPermissionTree", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> getMyPermissionTree(){
//        List<URole> roles = roleService.findNowAllPermissions();
//        List<Map<String, Object>> list = new LinkedList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("text","text");
//        map.put("href","url");
//        List<Map<String, Object>> listItem = new LinkedList<>();
//        Map<String, Object> mapx = new HashMap<>();
//        mapx.put("text","text01");
//        mapx.put("href","url01");
//        listItem.add(mapx);
//        map.put("nodes",listItem);
//        list.add(map);
//        return list;
        //        List<Map<String, Object>> data = UserManager.getPermissionTree(roles);
//        return data;
        //得到当前用户的角色信息roles，里面包括了permissions信息

        List<Map<String, Object>> treeData = userManager.getPermissionTree();
        return treeData;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRole(URole role){
        int index = roleService.insertSelective(role);
        if(index != 0){
            resultMap.put("status", 200);
            resultMap.put("message", "添加角色成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("message", "添加角色失败");
            LoggerUtils.fmtDebug(getClass(), "添加角色失败 source[%s]", role.toString());
        }
        return resultMap;
    }

    @RequestMapping(value = "/deleteRoleById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRoleById(String ids){
        return roleService.deleteRoleById(ids);
    }

}
