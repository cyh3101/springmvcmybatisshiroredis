package com.cyh.permission.bo;

import com.cyh.common.model.URole;

/**
 * Created by cai on 2017/8/31.
 */
public class RolePermissionAllocationBo extends URole{

    //角色的权限ids，以","隔开
    private String permissionIds;

    //角色的权限名称,以","隔开
    private String permissionNames;

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String permissionNames) {
        this.permissionNames = permissionNames;
    }
}
