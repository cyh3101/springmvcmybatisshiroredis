package com.cyh.common.model;

import java.io.Serializable;

public class URole implements Serializable{
    private Long id;

    private String name;

    private String type;

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

    @Override
    public String toString() {
        return "URole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}