package com.cyh.user.bo;

import com.cyh.common.model.UUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cai on 2017/8/20.
 */
public class UserOnlineBo extends UUser implements Serializable{

    private String sessionId;

    private String host;//Session host

    private Date createTime;//创建时间

    private Date lastAccessTime;//最后活动时间

    private long timeout;//Session Timeout

    private boolean sessionStatus = Boolean.TRUE;

    public UserOnlineBo(){}

    public UserOnlineBo(UUser user){super(user);}

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
