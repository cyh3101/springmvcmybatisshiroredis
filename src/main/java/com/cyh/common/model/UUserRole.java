package com.cyh.common.model;

import java.io.Serializable;

public class UUserRole implements Serializable{
    private Long uid;

    private Long rid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}