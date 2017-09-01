package com.cyh.permission.bo;

import com.cyh.common.model.UPermission;
import com.cyh.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by cyh3101 on 2017/9/1.
 */
public class RolePermissionBo extends UPermission implements Serializable{
    private String roleId;

    private String mark;

    public boolean isChecked(){
        return StringUtils.equals(roleId, mark);
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
