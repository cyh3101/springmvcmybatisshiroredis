package com.cyh.core.shiro.token.manager;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * Created by cai on 2017/8/1.
 */
public class ShiroToken extends UsernamePasswordToken implements Serializable{
    private String email;
    private String pswd;
    public ShiroToken(String email, String pswd){
        this.email = email;
        this.pswd = pswd;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
