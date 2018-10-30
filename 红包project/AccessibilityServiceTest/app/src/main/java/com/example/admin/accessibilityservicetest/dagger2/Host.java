package com.example.admin.accessibilityservicetest.dagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liukan on 2018/7/24.
 */
public class Host {

    private String mName;
    @Inject
    public Host(String name) {
        Log.e("lk",name);
        this.mName = name;
    }

    public String getmName() {
        return mName;
    }
}
