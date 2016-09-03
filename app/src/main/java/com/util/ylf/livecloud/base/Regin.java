package com.util.ylf.livecloud.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.util.ylf.livecloud.Login;
import com.util.ylf.livecloud.R;
import com.util.ylf.livecloud.modle.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/3.
 */
public class Regin extends Activity {
    private EditText et_password;
    private  EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_regin);
        initView();

    }

    private void initView() {
        et_password= (EditText) findViewById(R.id.password_et_regin);
        et_username= (EditText) findViewById(R.id.name_et_regin);

    }

    public void onRegin(View view){
        String name = et_username.getText().toString();
        String password=et_password.getText().toString();
        if(name==null){
            Toast.makeText(Regin.this, "name is null", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password==null){
            Toast.makeText(Regin.this, "password is null", Toast.LENGTH_SHORT).show();
            return;
        }

        User user=new User(name,password);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(Regin.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Regin.this.startActivity(new Intent(Regin.this, Login.class));
                }else
                {
                    Toast.makeText(Regin.this, "注册失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
