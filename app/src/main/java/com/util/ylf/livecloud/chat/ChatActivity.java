package com.util.ylf.livecloud.chat;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.util.ylf.livecloud.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ChatActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    /*
      *  表情包
     */
    private LinearLayout emb_lay;
    private ImageView swift_em;
    private RadioGroup group;
    private GridView gv_emb;
    private EditText et_input;

    private Button btn_send;

    private ExpressAdapter expressAdapter;


    private String expressType = "http://121.40.227.121:8080/AccumulateWealth/jucaipen/getexpresstype";

    private String expressListr = "http://121.40.227.121:8080/AccumulateWealth/jucaipen/getexpressinfo";


    private List<Express> expresses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat);
        initView();
        initData();
        group.removeAllViews();
    }


    private void initExpressList(int typeId) {
        RequestParams param = new RequestParams(expressListr);
        param.addParameter("packageId", typeId);
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    //   {"id":3,"typeId":1,"title":"","faceUrl":"http://img.jucaipen.com/jucaipenUpload/2015/7/15/2015715174128.gif"}
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            int typeId = object.getInt("typeId");
                            String title = object.getString("title");
                            String faceUrl = object.getString("faceUrl");
                            Express express = new Express(id, typeId, title, faceUrl);
                            expresses.add(express);
                        }
                        expressAdapter.setExpresses(expresses);
                        expressAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initData() {
        RequestParams param = new RequestParams(expressType);
        x.http().get(param, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String typeName = object.getString("typeName");
                            RadioButton button = new RadioButton(ChatActivity.this);
                            button.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
                            button.setPadding(10, 10, 10, 10);
                            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.WRAP_CONTENT);
                            lp.weight = 1.0f;
                            button.setLayoutParams(lp);
                            button.setText(typeName);
                            button.setGravity(Gravity.CENTER);
                            button.setId(id);
                            group.addView(button, i);
                        }

                        if (group.getChildCount() > 0) {
                            RadioButton rb = (RadioButton) group.getChildAt(0);
                            rb.setChecked(true);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        emb_lay = (LinearLayout) findViewById(R.id.emb_lay);
        swift_em = (ImageView) findViewById(R.id.swift_em);
        group = (RadioGroup) findViewById(R.id.group);
        gv_emb = (GridView) findViewById(R.id.gv_emb);
        expressAdapter = new ExpressAdapter(expresses, this);
        gv_emb.setAdapter(expressAdapter);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        group.setOnCheckedChangeListener(this);
        gv_emb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Editable eb = et_input.getEditableText();
                //获得光标所在位置
                int qqPosition = et_input.getSelectionStart();
                SpannableString ss = new SpannableString("cry");
                //定义插入图片
                ImageView iv = (ImageView) view.findViewById(R.id.iv_express);
                Drawable drawable = iv.getDrawable();
                ss.setSpan(new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                drawable.setBounds(2, 0, 50, 50);
                //插入图片
                eb.insert(qqPosition, ss);
            }
        });

        swift_em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emb_lay.getVisibility() == View.VISIBLE) {
                    emb_lay.setVisibility(View.GONE);
                } else {
                    emb_lay.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        expresses.clear();
        initExpressList(checkedId);
    }

    @Override
    public void onClick(View v) {
        String text = et_input.getText().toString();
        if (text != null) {

        }

    }
}
