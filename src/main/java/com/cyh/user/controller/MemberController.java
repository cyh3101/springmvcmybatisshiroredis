package com.cyh.user.controller;

import com.cyh.common.controller.BaseController;
import com.cyh.common.model.UUser;
import com.cyh.core.mybatis.page.Pagination;
import com.cyh.core.shiro.CustomSessionManager;
import com.cyh.core.shiro.cache.impl.CustomCacheManager;
import com.cyh.user.bo.UserOnlineBo;
import com.cyh.user.service.UUserService;
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
 * Created by cyh3101 on 2017/8/19.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController{
    @Autowired
    CustomSessionManager customSessionManager;
    @Autowired
    private UUserService uUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelMap map, Integer pageNo, String findContent){
        map.put("findContent",findContent);
        Pagination<UUser> page = uUserService.findByPage(map, pageNo, pageSize);
        map.put("page", page);
        return new ModelAndView("member/test");
//        return new ModelAndView("member/list");
    }

    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public ModelAndView online(){
        List<UserOnlineBo> onlineList = customSessionManager.getAllUser();
        return new ModelAndView("member/online", "list", onlineList);
    }

    @RequestMapping(value = "/forbidUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> forbidUserById(Long status, Long id){
        System.out.println("forbidUserById 被调用了");
        return  uUserService.forbidUserById(status, id);
    }

    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteUserById(String id){
        return uUserService.deleteById(id);
//        System.out.println("deleteUserById 被调用了");
//        Map<String, Object> resultMap = new HashMap<>();
//        Integer deleteId = uUserService.deleteByPrimaryKey(id);
//        if(deleteId != 0){
//            resultMap.put("status",200);
//            resultMap.put("message","删除成功");
//        } else {
//            resultMap.put("status", 500);
//            resultMap.put("message","删除失败");
//        }
//        return resultMap;
    }
}
