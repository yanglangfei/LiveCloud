package com.util.ylf.livecloud;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ChatActivity extends Activity {

    /*
      *  表情包
     */
    private LinearLayout emb_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat);
        initView();

    }

    private void initView() {
        emb_lay=(LinearLayout)findViewById(R.id.emb_lay);
    }
}
