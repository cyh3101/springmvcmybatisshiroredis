package com.cyh.common.model;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class URole implements Serializable{
    private Long id;

    private String name;

    private String type;

    private List<UPermission> permissions = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public List<UPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UPermission> permissions) {
        this.permissions = permissions;
    }
    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}