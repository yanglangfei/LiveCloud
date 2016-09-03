package com.util.ylf.livecloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ylf.livecloud.base.Regin;
import com.util.ylf.livecloud.chat.ChatMain;
import com.util.ylf.livecloud.modle.User;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2016/9/3.
 * <p/>
 * 登录
 */
public class Login extends Activity implements View.OnClickListener {
    private EditText name_et;

    private  EditText password_et;
    private TextView to_regin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_login);
        initView();
    }

    private void initView() {
        name_et= (EditText) findViewById(R.id.name_et);
        password_et= (EditText) findViewById(R.id.password_et);
        to_regin= (TextView) findViewById(R.id.to_regin);
        to_regin.setOnClickListener(this);
    }

    public void onLogin(View view) {
        String name = name_et.getText().toString();
        String passord=password_et.getText().toString();
        if(name==null){
            Toast.makeText(Login.this, "username is null", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if(passord==null){
            Toast.makeText(Login.this, "password is null", Toast.LENGTH_SHORT).show();
            return;
        }

        BmobQuery<User>  query=new BmobQuery<>();
        query.addWhereEqualTo("username", name);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e != null) {
                    Toast.makeText(Login.this, "unKnow user", Toast.LENGTH_SHORT).show();
                }

                if (list != null && list.size() > 0) {
                    final String id = list.get(0).getObjectId();
                    BmobIM.connect(id, new ConnectListener() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, ChatMain.class));
                                changeState(1,id);
                            } else {
                                changeState(0,id);
                                Toast.makeText(Login.this, "登录失败" + s, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }

    private void changeState(int i,String id) {
        User user=new User();
        user.setState(i+"");
        user.update(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Regin.class));
    }
}
