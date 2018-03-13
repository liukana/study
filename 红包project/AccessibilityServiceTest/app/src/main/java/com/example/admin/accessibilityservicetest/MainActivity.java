package com.example.admin.accessibilityservicetest;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Context context;
    private Button btOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        btOpenHelper = findViewById(R.id.btOpenHelper);
        btOpenHelper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btOpenHelper:
                jumpToSettingPage();
                break;
        }
    }

    public void jumpToSettingPage() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
        } catch (Exception ignore) {}
    }
}
