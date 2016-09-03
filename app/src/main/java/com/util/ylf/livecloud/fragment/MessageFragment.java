package com.util.ylf.livecloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.ylf.livecloud.R;

/**
 * Created by Administrator on 2016/9/3.
 */
public class MessageFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ui_message, container, false);
        initData();
        return view;
    }

    private void initData() {
        //在聊天页面的onCreate方法中，通过如下方法创建新的会话实例,这个obtain方法才是真正创建一个管理消息发送的会话
      /*  BmobIMConversation conversation=BmobIMConversation.obtain(BmobIMClient.getInstance(),"c");
        conversation.queryMessages(null, 10, new MessagesQueryListener() {
            @Override
            public void done(List<BmobIMMessage> list, BmobException e) {
                if(e!=null){
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("111","li:"+list.size());
            }
        });*/

    }
}
