package com.cyh.core.shiro.session;

import java.io.Serializable;

/**
 * Created by cyh on 2017/8/21.
 */
public class SessionStatus implements Serializable{

    private Boolean onlineStatus = Boolean.TRUE;

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public boolean isOnlineStatus(){
        return onlineStatus;
    }
}
