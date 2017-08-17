package com.cyh.core.shiro;

import com.cyh.common.model.UUser;
import com.cyh.core.shiro.token.manager.ShiroToken;
import com.cyh.permission.service.PermissionService;
import com.cyh.permission.service.RoleService;
import com.cyh.user.service.UUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

/**
 * Created by cai on 2017/7/13.
 */
public class SampleRealm extends AuthorizingRealm{
    @Autowired
    private UUserService uUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String email = principalCollection.getPrimaryPrincipal().toString();
        UUser user = uUserService.findUserByEmail(email);
        Set<String> permissions = permissionService.findPermissionByUserId(user.getId());
        info.setStringPermissions(permissions);

        Set<String> roles = roleService.findRoleByUserId(user.getId());
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken)authenticationToken;
        UUser user = uUserService.login((String)token.getEmail() , new String(token.getPassword()));
        System.out.println("=================info===========================");
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
