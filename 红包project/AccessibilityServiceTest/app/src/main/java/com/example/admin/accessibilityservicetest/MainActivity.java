package com.example.admin.accessibilityservicetest;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.accessibilityservicetest.Service.MyAccessibilityService;
import com.example.admin.accessibilityservicetest.aspectj.AddLog;
import com.example.admin.accessibilityservicetest.bean.ChatBackBean;
import com.example.admin.accessibilityservicetest.bean.ChatContent;
import com.example.admin.accessibilityservicetest.network.RequestManager;
import com.example.admin.accessibilityservicetest.network.TuLingRobot;
import com.example.admin.accessibilityservicetest.util.JsonUtils;
import com.example.admin.accessibilityservicetest.wifi.WifiActivity;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String TAG = MainActivity.class.getSimpleName();
    private static Context context;
    private Button btOpenHelper;
    private Button btWeChat;
    private Button btDingding;
    private Button btnJump;
    private static MyAccessibilityService myAccessibilityService = new MyAccessibilityService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        btOpenHelper = findViewById(R.id.btOpenHelper);
        btDingding = findViewById(R.id.btDingding);
        btWeChat = findViewById(R.id.btWeChat);
        btnJump = findViewById(R.id.btnJump);
        btOpenHelper.setOnClickListener(this);
        btDingding.setOnClickListener(this);
        btWeChat.setOnClickListener(this);
        btnJump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btOpenHelper:
                jumpToSettingPage();
                break;
            case R.id.btDingding:
                myAccessibilityService.setApplication(new DingDingForRedEnvelope());
                break;
            case R.id.btWeChat:
                myAccessibilityService.setApplication(new WeiXinForRedEnvelope());
                ChatContent content = new ChatContent();

                ChatContent.PerceptionBean perceptionBean = new ChatContent.PerceptionBean();
                ChatContent.PerceptionBean.InputTextBean inputTextBean = new ChatContent.PerceptionBean.InputTextBean();
                inputTextBean.setText("hello");
                perceptionBean.setInputText(inputTextBean);


                ChatContent.UserInfoBean userInfoBean = new ChatContent.UserInfoBean();
                userInfoBean.setApiKey("4c17830884804bfd9ae582132d13aff8");
                userInfoBean.setUserId("liukan");

                content.setPerception(perceptionBean);
                content.setUserInfo(userInfoBean);

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJson(content));
                Call<ChatBackBean> call = RequestManager.getInstance().create(TuLingRobot.class).getMsg(body);
                call.enqueue(new Callback<ChatBackBean>() {
                    @Override
                    public void onResponse(Call<ChatBackBean> call, Response<ChatBackBean> response) {
                        Log.e(TAG,response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ChatBackBean> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnJump:
                Intent intent = new Intent(MainActivity.this, WifiActivity.class);
                context.startActivity(intent);
                break;
        }
    }
    @AddLog
    public static void jumpToSettingPage() {
        try {
            Log.d(TAG,"进入系统测试页面");
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
        } catch (Exception ignore) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
