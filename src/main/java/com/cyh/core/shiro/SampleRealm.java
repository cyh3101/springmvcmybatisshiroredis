package com.cyh.core.shiro;

import com.cyh.common.model.UUser;
import com.cyh.common.model.UUserRole;
import com.cyh.permission.service.PermisionService;
import com.cyh.permission.service.RoleService;
import com.cyh.user.service.UUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by cai on 2017/7/13.
 */
public class SampleRealm extends AuthorizingRealm{
    @Autowired
    private UUserService uUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermisionService permisionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        UUser user = uUserService.login((String)token.getPrincipal() , (String)token.getCredentials());
        if(null == user){
            throw new AccountException("账号或密码错误");
        }else if(user.getStatus() == 0){
            throw new DisabledAccountException("账号禁用");
        }else {
            user.setLastLoginTime(new Date());
            uUserService.updateByPrimaryKeySelective(user);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user , user.getPswd() , getName());
        return info;
    }
}
