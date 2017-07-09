package com.cyh.common.bo;

import com.cyh.common.model.UPermission;

import java.io.Serializable;

/**
 * Created by cai on 2017/7/9.
 */
public class UPermissionBo extends UPermission implements Serializable{
    public Long roleId;

    public String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
