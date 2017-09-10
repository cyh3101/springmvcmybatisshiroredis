package com.cyh.user.manager;

import com.cyh.common.model.UPermission;
import com.cyh.common.model.URole;
import com.cyh.common.model.UUser;
import com.cyh.common.utils.MathUtil;
import com.cyh.common.utils.SerializeUtil;
import com.cyh.core.CustomCachManager;
import com.cyh.core.shiro.cache.JedisManager;
import com.cyh.core.shiro.cache.impl.CustomCacheManager;
import com.cyh.core.shiro.token.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cai on 2017/7/15.
 */
public class UserManager {
    private static final String PERMISSION_TREE = "PERMISSION_TREE";
    private static final int DB_INDEX = 1;


    private  JedisManager jedisManager ;

    public  void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }

    public JedisManager getJedisManager(){
        return this.jedisManager;
    }

    public static UUser getMD5(UUser user){
        user.setPswd(getMD5(user.getPswd() , user.getEmail()));
        return user;
    }

    public static String getMD5(String pwd , String email){
        pwd = String.format("%s#%s" , pwd , email);
        pwd = MathUtil.getMD5(pwd);
        return pwd;
    }

    /**
     * 返回树形结构
     * @param roles
     * @return
     */
    public  List<Map<String, Object>> getPermissionTree(List<URole> roles){
        //customCacheManager.getCache()
        List<Map<String, Object>> treeData = new LinkedList<>();
        String keyvalue = PERMISSION_TREE + ":" + TokenManager.getSession().getId().toString();
        byte[] bytekey = SerializeUtil.serialize(keyvalue);
        byte[] bytevalue = null;
        if(jedisManager.isExists(bytekey) && jedisManager.getValueByKey(DB_INDEX, bytekey) != null){
            bytevalue = jedisManager.getValueByKey(DB_INDEX, bytekey);
            treeData = SerializeUtil.deserialize(bytevalue);
        }else {
            for (URole role:roles
                    ) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("text", role.getName());//角色节点标题
                map.put("href", "javascript:void(0);");//角色节点链接
                //角色节点的子节点
                List<UPermission> permissions = role.getPermissions();
                map.put("tags", new Integer[]{permissions.size()});
                if(permissions != null && permissions.size() != 0){
                    List<Map<String, Object>> item = new LinkedList<>();
                    for (UPermission permission:permissions
                            ) {
                        Map<String, Object> mapx = new LinkedHashMap<>();
                        mapx.put("text", permission.getName());
                        mapx.put("href", permission.getUrl());
                        mapx.put("tags", new Integer[]{0});
                        item.add(mapx);
                    }
                    map.put("nodes", item);
                }
                treeData.add(map);
            }
            bytevalue = SerializeUtil.serialize(treeData);
            jedisManager.saveValueByKey(DB_INDEX, bytekey, bytevalue,-1);
        }

        return treeData;
    }
}
