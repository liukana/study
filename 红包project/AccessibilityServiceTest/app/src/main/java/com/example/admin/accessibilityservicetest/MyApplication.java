package com.example.admin.accessibilityservicetest;

import android.app.Application;
import android.content.Context;

import com.example.admin.accessibilityservicetest.density.DensityUpdateUtil;

/**
 * Created by liukan on 2018/9/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        DensityUpdateUtil.setCustomDensity(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
