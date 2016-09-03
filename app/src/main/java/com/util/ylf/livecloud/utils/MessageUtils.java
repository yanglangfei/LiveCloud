package com.util.ylf.livecloud.utils;

import android.content.Context;
import android.widget.Toast;

import com.util.ylf.livecloud.base.Config;
import com.util.ylf.livecloud.message.AddFriendMessage;
import com.util.ylf.livecloud.message.AgreeAddFriendMessage;
import com.util.ylf.livecloud.message.NewFriendManager;
import com.util.ylf.livecloud.modle.NewFriend;
import com.util.ylf.livecloud.modle.User;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/3.
 */
public class MessageUtils  {

    /**
     * @param info
     * @param context
     *
     *    发送好友申请
     */
    public  void  sendAddFriendMessage(BmobIMUserInfo info, final Context context){
        // //启动一个暂态会话，也就是isTransient为true,表明该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中，
        BmobIMConversation c= BmobIM.getInstance().startPrivateConversation(info,true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation=BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
        //新建一个添加好友的自定义消息实体
        AddFriendMessage addFriendMessage=new AddFriendMessage();
        User currentUser= BmobUser.getCurrentUser(User.class);
        addFriendMessage.setContent("\"很高兴认识你，可以加个好友吗?\"");
        Map<String,Object>  map=new HashMap<>();
        map.put("name", currentUser.getUsername());//发送者姓名，这里只是举个例子，其实可以不需要传发送者的信息过去
        map.put("avatar", currentUser.getFaceImage());//发送者的头像
        map.put("uid",currentUser.getObjectId());//发送者的uid
        addFriendMessage.setExtraMap(map);

        conversation.sendMessage(addFriendMessage, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if(e==null){
                    Toast.makeText(context, "好友申请发送成功，等待同意", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "好友申请发送失败，等待同意", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * @param add
     * @param listener
     *
     *   发送同意好友
     */
    public  void agreeFriendMessage(final NewFriend add,final SaveListener listener, final Context context){
        //发给谁，就填谁的用户信息
        BmobIMUserInfo toInfo=new BmobIMUserInfo(add.getUid(), add.getName(), add.getAvatar());

        //启动一个暂态会话，也就是isTransient为true,表明该会话仅执行发送消息的操作，不会保存会话和消息到本地数据库中，
        BmobIMConversation c= BmobIM.getInstance().startPrivateConversation(toInfo,true,null);
        //这个obtain方法才是真正创建一个管理消息发送的会话
        BmobIMConversation conversation = BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
        //而AgreeAddFriendMessage的isTransient设置为false，表明我希望在对方的会话数据库中保存该类型的消息
        AgreeAddFriendMessage msg =new AgreeAddFriendMessage();
        User currentUser = BmobUser.getCurrentUser(User.class);
        msg.setContent("我通过了你的好友验证请求，我们可以开始聊天了!");//---这句话是直接存储到对方的消息表中的
        Map<String,Object> map =new HashMap<>();
        map.put("msg",currentUser.getUsername()+"同意添加你为好友");//显示在通知栏上面的内容
        map.put("uid",add.getUid());//发送者的uid-方便请求添加的发送方找到该条添加好友的请求
        map.put("time", add.getTime());//添加好友的请求时间
        msg.setExtraMap(map);

        conversation.sendMessage(msg, new MessageSendListener() {
            @Override
            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                if(e==null){
                    NewFriendManager.getInstance(context).updateNewFriend(add, Config.STATUS_VERIFIED);
                    Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
