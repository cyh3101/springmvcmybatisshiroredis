package com.cyh.permission.bo;

import com.cyh.common.model.URole;
import com.cyh.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by cai on 2017/8/30.
 */
public class UserRoleBo extends URole implements Serializable{
    private String userId;

    private String marker;

    public boolean isChecked(){
        return StringUtils.equals(userId, marker);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
