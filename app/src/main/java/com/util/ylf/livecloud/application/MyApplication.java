package com.util.ylf.livecloud.application;

import android.app.Application;

import com.util.ylf.livecloud.handle.MyMessageHandler;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/9/3.
 */
public class MyApplication extends Application {

    private String appkey="fc331473a8c37895b2d8eed13aa2b88a";

    private static MyApplication INSTANCE;
    public static MyApplication INSTANCE(){
        return INSTANCE;
    }
    private void setInstance(MyApplication app) {
        setBmobIMApplication(app);
    }
    private static void setBmobIMApplication(MyApplication a) {
        MyApplication.INSTANCE = a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        if (getApplicationInfo().packageName.equals(getMyProcessName())) {
            Bmob.initialize(this, appkey);
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new MyMessageHandler());
        }
    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
