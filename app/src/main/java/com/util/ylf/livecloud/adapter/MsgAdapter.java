package com.util.ylf.livecloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.util.ylf.livecloud.Msg;
import com.util.ylf.livecloud.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class MsgAdapter extends BaseAdapter {
    private final List<Msg> msgList;
    private final Context context;

    public MsgAdapter(List<Msg> msgList, Context context) {
        this.msgList=msgList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return msgList.get(position).getMsgType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type=getItemViewType(position);
        if(type==0){
            //收到消息
             convertView= LayoutInflater.from(context).inflate(R.layout.receiver_msg_item,null);
        }else {
            //发出消息

        }
        return convertView;
    }
}
