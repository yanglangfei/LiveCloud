package com.util.ylf.livecloud;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Msg extends BmobObject {

    private  int msgType;
    private  String fromName;

    private  String toName;

    private  String fromFace;


    private  String toFace;


    private  int id;

    private  String msg;

    private  int fromId;

    private  int toId;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromFace() {
        return fromFace;
    }

    public void setFromFace(String fromFace) {
        this.fromFace = fromFace;
    }

    public String getToFace() {
        return toFace;
    }

    public void setToFace(String toFace) {
        this.toFace = toFace;
    }

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
