package com.util.ylf.livecloud.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/3.
 */
public class Friend extends BmobObject {
    //用户
    private User user;
    //好友
    private User friendUser;

    public Friend(User friendUser, User user) {
        this.friendUser = friendUser;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }
}
