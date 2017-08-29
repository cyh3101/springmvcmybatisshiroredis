package com.cyh.permission.bo;

import com.cyh.common.model.UUser;

import java.io.Serializable;

/**
 * Created by cyh on 2017/8/29.
 */
public class UserRoleAllocationBo extends UUser implements Serializable{

    //用户角色名字，以","隔开
    private String roleNames;

    //用户角色id,以","隔开
    private String roleIds;

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
