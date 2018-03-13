package com.example.admin.accessibilityservicetest;

import android.view.accessibility.AccessibilityEvent;

/**
 * Created by liukan on 2018/3/9.
 */

public interface WorkForRedEnvelope {

    /**
     * 处理通知，进入应用
     * @param event
     */
    public void handleNotification(AccessibilityEvent event);

    /**
     * 进入对应聊天界面
     */
    public void goToChattingPage();

    /**
     * 抢红包
     */
    public void openPacket();

    /**
     * 退出红包详情页面
     */
    public void close();
}
