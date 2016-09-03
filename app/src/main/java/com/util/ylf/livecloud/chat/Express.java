package com.util.ylf.livecloud.chat;

/**
 * Created by Administrator on 2016/9/3.
 */
public class Express {


    /**
     * id : 3
     * typeId : 1
     * title :
     * faceUrl : http://img.jucaipen.com/jucaipenUpload/2015/7/15/2015715174128.gif
     */

    private int id;
    private int typeId;
    private String title;
    private String faceUrl;

    public Express(int id, int typeId, String title, String faceUrl) {
        this.id = id;
        this.typeId = typeId;
        this.title = title;
        this.faceUrl = faceUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }

    public String getFaceUrl() {
        return faceUrl;
    }
}
