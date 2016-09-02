package com.util.ylf.livecloud;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Msg extends BmobObject {


    private  int id;

    private  String msg;

    private  int fromId;

    private  int toId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }
}
