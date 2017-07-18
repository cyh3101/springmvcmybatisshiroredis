package com.cyh.core.shiro;

import com.cyh.common.model.UUser;
import com.cyh.user.manager.UserManager;
import com.cyh.user.service.UUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cai on 2017/7/16.
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher{
    @Autowired
    private UUserService uUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

//        String email = (String)token.getPrincipal();
//        UUser user = uUserService.findUserByEmail(email);
//        user = UserManager.getMD5(user);
//        System.out.println("user=====" + user.toString());
//        System.out.println("=========================================");
//        System.out.println(info.getCredentials());
        return this.equals(token.getCredentials() , info.getCredentials());


        //return super.doCredentialsMatch(token, info);
    }
}
