package com.example.admin.accessibilityservicetest;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.accessibilityservicetest.Service.MyAccessibilityService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btOpenHelper;
    private Button btWeChat;
    private Button btDingding;
    private static MyAccessibilityService myAccessibilityService = new MyAccessibilityService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        btOpenHelper = findViewById(R.id.btOpenHelper);
        btDingding = findViewById(R.id.btDingding);
        btWeChat = findViewById(R.id.btWeChat);
        btOpenHelper.setOnClickListener(this);
        btDingding.setOnClickListener(this);
        btWeChat.setOnClickListener(this);
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
                break;
        }
    }

    public void jumpToSettingPage() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
        } catch (Exception ignore) {
        }
    }
}
