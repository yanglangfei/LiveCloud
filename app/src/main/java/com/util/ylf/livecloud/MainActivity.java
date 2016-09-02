package com.util.ylf.livecloud;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.duanqu.qupai.sdk.qupailiverecord.live.DQLiveMediaFormat;
import com.duanqu.qupai.sdk.qupailiverecord.live.DQLiveMediaRecorder;
import com.duanqu.qupai.sdk.qupailiverecord.live.DQLiveMediaRecorderFactory;
import com.duanqu.qupai.sdk.qupailiverecord.live.DQLiveStatusCode;
import com.duanqu.qupai.sdk.qupailiverecord.live.OnLiveRecordErrorListener;
import com.duanqu.qupai.sdk.qupailiverecord.live.OnNetworkStatusListener;
import com.duanqu.qupai.sdk.qupailiverecord.live.OnRecordStatusListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements SurfaceHolder.Callback, OnLiveRecordErrorListener, OnRecordStatusListener, OnNetworkStatusListener {

    private String accessToken;
    private String space;
    private String appsecret;
    private String appKey;
    private SurfaceView sv;
    private SurfaceHolder holder;
    private Surface surface;
    private DQLiveMediaRecorder recorder;
    private Button take;
    private Map<String, Object> mConfigure = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        sv = (SurfaceView) findViewById(R.id.sv);
        holder = sv.getHolder();
        holder.addCallback(this);
        recorder = DQLiveMediaRecorderFactory.createMediaRecorder();
        recorder.init(this);
        mConfigure.put(DQLiveMediaFormat.KEY_CAMERA_FACING, DQLiveMediaFormat.CAMERA_FACING_BACK);
        mConfigure.put(DQLiveMediaFormat.KEY_MAX_ZOOM_LEVEL, 3);
        mConfigure.put(DQLiveMediaFormat.KEY_OUTPUT_RESOLUTION, DQLiveMediaFormat.OUTPUT_RESOLUTION_1080P);
        mConfigure.put(DQLiveMediaFormat.KEY_MAX_VIDEO_BITRATE, 800000);
        mConfigure.put(DQLiveMediaFormat.KEY_DISPLAY_ROTATION, DQLiveMediaFormat.DISPLAY_ROTATION_90);
        mConfigure.put(DQLiveMediaFormat.KEY_EXPOSURE_COMPENSATION, -1);//曝光度-1为自动曝光

        recorder.setOnRecordErrorListener(this);
        recorder.setOnRecordStatusListener(this);
        recorder.setOnNetworkStatusListener(this);

        recorder.addFlag(DQLiveMediaFormat.FLAG_BEAUTY_ON);//开启美颜
        recorder.prepare(mConfigure, surface);

        take = (Button) findViewById(R.id.take);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.startRecord(Contants.playUrl_MU);
            }
        });
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        holder.setKeepScreenOn(true);
        surface = holder.getSurface();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        recorder.setPreviewSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        sv = null;
        recorder.stopRecord();
        recorder.reset();


    }

    @Override
    public void onError(int i) {
        switch (i) {
            case DQLiveStatusCode.ERORR_OUT_OF_MEMORY:
                Log.i("111", "error:" + "内存不足");
                break;
            case DQLiveStatusCode.ERROR_IO:
                Log.i("111", "error:" + "IO");
                break;
            case DQLiveStatusCode.ERROR_BROKEN_PIPE:
                Log.i("111", "error:" + "管道中断");
                break;
            case DQLiveStatusCode.ERROR_ILLEGAL_ARGUMENT:
                Log.i("111", "error:" + "非法参数");
                break;
            case DQLiveStatusCode.ERROR_NETWORK_UNREACHABLE:
                Log.i("111", "error:" + "网络不可达");
                break;
            case DQLiveStatusCode.ERROR_SERVER_CLOSED_CONNECTION:
                Log.i("111", "error:" + "服务断开连接");
                break;
            case DQLiveStatusCode.ERROR_CONNECTION_TIMEOUT:
                Log.i("111", "error:" + "连接超时");
                break;
            default:
                break;
        }


    }


    @Override
    public void onDeviceAttach() {

    }

    @Override
    public void onDeviceAttachFailed(int i) {

    }

    @Override
    public void onSessionAttach() {

    }

    @Override
    public void onSessionDetach() {

    }

    @Override
    public void onDeviceDetach() {

    }

    @Override
    public void onIllegalOutputResolution() {

    }

    @Override
    public void onNetworkBusy() {
        Log.i("111", "net busy");

    }

    @Override
    public void onNetworkFree() {

    }

    @Override
    public void onConnectionStatusChange(int i) {

    }

    @Override
    public boolean onNetworkReconnect() {
        return false;
    }
}
