package com.util.ylf.livecloud.chat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.util.ylf.livecloud.R;
import com.util.ylf.livecloud.modle.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/3.
 */
public class ChatMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
    }

    public  void onAdd(View view){
        User user=new User("www","13453");
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(ChatMain.this, "save s", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(ChatMain.this, "save ee", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
