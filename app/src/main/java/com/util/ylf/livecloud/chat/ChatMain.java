package com.util.ylf.livecloud.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.util.ylf.livecloud.R;
import com.util.ylf.livecloud.adapter.MainPageAdapter;
import com.util.ylf.livecloud.fragment.ContactFragment;
import com.util.ylf.livecloud.fragment.MessageFragment;
import com.util.ylf.livecloud.fragment.SettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class ChatMain extends FragmentActivity {

    private RadioGroup main_group;

    private ViewPager fragment;
    private MessageFragment messageFragment;
    private ContactFragment contactFragment;
    private SettingFragment settingFragment;
    private MainPageAdapter adapter;

    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        initView();
    }

    private void initView() {
        main_group= (RadioGroup) findViewById(R.id.main_group);
        fragment= (ViewPager) findViewById(R.id.fragment);
        messageFragment=new MessageFragment();
        contactFragment=new ContactFragment();
        settingFragment=new SettingFragment();
        fragments.add(messageFragment);
        fragments.add(contactFragment);
        fragments.add(settingFragment);

        adapter=new MainPageAdapter(getSupportFragmentManager(),fragments);
        fragment.setAdapter(adapter);


    }

}
