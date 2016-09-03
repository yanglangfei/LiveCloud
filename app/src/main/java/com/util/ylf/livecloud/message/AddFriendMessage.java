package com.util.ylf.livecloud.message;

import android.text.TextUtils;
import android.util.Log;

import com.util.ylf.livecloud.base.Config;
import com.util.ylf.livecloud.modle.NewFriend;

import org.json.JSONObject;

import cn.bmob.newim.bean.BmobIMExtraMessage;
import cn.bmob.newim.bean.BmobIMMessage;

/**
 * 添加好友请求-自定义消息类型
 */
public class AddFriendMessage extends BmobIMExtraMessage {

    public AddFriendMessage(){}

    /**将BmobIMMessage转成NewFriend
     * @param msg 消息
     * @return
     */
    public static NewFriend convert(BmobIMMessage msg){
        NewFriend add =new NewFriend();
        String content = msg.getContent();
        add.setMsg(content);
        add.setTime(msg.getCreateTime());
        add.setStatus(Config.STATUS_VERIFY_NONE);
        try {
            String extra = msg.getExtra();
            if(!TextUtils.isEmpty(extra)){
                JSONObject json =new JSONObject(extra);
                String name = json.getString("name");
                add.setName(name);
                String avatar = json.getString("avatar");
                add.setAvatar(avatar);
                add.setUid(json.getString("uid"));
            }else{
                Log.i("111","AddFriendMessage的extra为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return add;
    }


    @Override
    public String getMsgType() {
        //自定义一个`add`的消息类型
        return "add";
    }

    @Override
    public boolean isTransient() {
        //设置为true,表明为暂态消息，那么这条消息并不会保存到本地db中，SDK只负责发送出去
        //设置为false,则会保存到指定会话的数据库中
        return true;
    }



}