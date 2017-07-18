package com.cyh.user.manager;

import com.cyh.common.model.UUser;
import com.cyh.common.utils.MathUtil;

/**
 * Created by cai on 2017/7/15.
 */
public class UserManager {
    public static UUser getMD5(UUser user){
        user.setPswd(getMD5(user.getPswd() , user.getEmail()));
        return user;
    }

    public static String getMD5(String pwd , String email){
        pwd = String.format("%s#%s" , pwd , email);
        pwd = MathUtil.getMD5(pwd);
        return pwd;
    }
}
