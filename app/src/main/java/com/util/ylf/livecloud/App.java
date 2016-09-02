package com.util.ylf.livecloud;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.duanqu.qupai.auth.AuthService;
import com.duanqu.qupai.auth.QupaiAuthListener;
import com.duanqu.qupai.httpfinal.QupaiHttpFinal;
import com.duanqu.qupai.jni.ApplicationGlue;

import cn.bmob.v3.Bmob;

/**
 * Created by administrator on 2016/6/13.
 */
public class App extends Application {
    private String appkey="fc331473a8c37895b2d8eed13aa2b88a";

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("qupai-media-thirdparty");
        System.loadLibrary("qupai-media-jni");
        ApplicationGlue.initialize(this);
        QupaiHttpFinal.getInstance().initOkHttpFinal();
        initAuth(getApplicationContext(), Contants.appkey, Contants.appsecret, Contants.space);
    }

    public void initBmobPush(){
        Bmob.initialize(this,appkey);
    }



    private static final String AUTHTAG = "QupaiAuth";

    /**
     * 鉴权 建议只调用一次,在Application调用。在demo里面为了测试调用了多次 得到accessToken
     * @param context
     * @param appKey    appkey
     * @param appsecret appsecret
     * @param space     space
     */
    private void initAuth(Context context , String appKey, String appsecret, String space){
        Log.e(AUTHTAG,"space" + Contants.space);

        AuthService service = AuthService.getInstance();
        service.setQupaiAuthListener(new QupaiAuthListener() {
            @Override
            public void onAuthError(int errorCode, String message) {
                Log.e(AUTHTAG, "ErrorCode" + errorCode + "message" + message);
            }

            @Override
            public void onAuthComplte(int responseCode, String responseMessage) {
                Log.e(AUTHTAG, "onAuthComplte" + responseCode + "message" + responseMessage);
                Contants.accessToken = responseMessage;
            }
        });
        service.startAuth(context,appKey, appsecret, space);
    }
}
