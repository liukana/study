package com.example.admin.accessibilityservicetest;

import android.view.accessibility.AccessibilityEvent;

import com.example.admin.accessibilityservicetest.Service.MyAccessibilityService;

/**
 * Created by liukan on 2018/3/9.
 */

public class ApplicationForRedEnvelope implements WorkForRedEnvelope{
    private static ApplicationForRedEnvelope applicationForRedEnvelope;
    protected MyAccessibilityService myAccessibilityService;

    public static ApplicationForRedEnvelope getInstance(){
        switch (1){
            case 1:
                applicationForRedEnvelope = new WeiXinForRedEnvelope();
                break;
            case 2:
                applicationForRedEnvelope = new DingDingForRedEnvelope();
            default:
                break;
        }
        return applicationForRedEnvelope;
    }

    public MyAccessibilityService getMyAccessibilityService() {
        return myAccessibilityService;
    }

    public void setMyAccessibilityService(MyAccessibilityService myAccessibilityService) {
        this.myAccessibilityService = myAccessibilityService;
    }

    @Override
    public void handleNotification(AccessibilityEvent event) {

    }

    @Override
    public void goToChattingPage() {

    }

    @Override
    public void openPacket() {

    }

    @Override
    public void close() {

    }
}
