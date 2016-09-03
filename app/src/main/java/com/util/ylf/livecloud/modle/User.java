package com.util.ylf.livecloud.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/3.
 */
public class User extends BmobObject{


    private  String username;

    private  String password;
    private  String state;

    public User() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
