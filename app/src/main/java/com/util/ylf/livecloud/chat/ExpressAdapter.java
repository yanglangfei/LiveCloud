package com.util.ylf.livecloud.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.util.ylf.livecloud.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class ExpressAdapter extends BaseAdapter {
    private  List<Express> expresses;
    private  Context context;

    public ExpressAdapter(List<Express> expresses, Context context) {
        this.expresses=expresses;
        this.context=context;
    }

    public void setExpresses(List<Express> expresses) {
        this.expresses = expresses;
    }

    @Override
    public int getCount() {
        return expresses.size();
    }

    @Override
    public Object getItem(int position) {
        return expresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if(convertView==null){
          convertView= LayoutInflater.from(context).inflate(R.layout.express_item,null);
      }
        ImageView iv_express= (ImageView) convertView.findViewById(R.id.iv_express);
        Glide.with(context).load(expresses.get(position).getFaceUrl()).into(iv_express);
        return convertView;
    }
}
