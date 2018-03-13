package com.example.admin.accessibilityservicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信版本：6.6.5
 * Created by liukan on 2018/3/9.
 */

public class WeiXinForRedEnvelope extends ApplicationForRedEnvelope {
    public static final String TAG = WeiXinForRedEnvelope.class.getSimpleName();

    @Override
    public void handleNotification(AccessibilityEvent event) {
        Log.d(TAG, "接收到红包通知！！");
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                Log.d(TAG, content);
                if (content.contains("[微信红包]")) {
                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void goToChattingPage() {
        Log.e(TAG, "进入聊天页面！！");
        AccessibilityNodeInfo nodeInfo = myAccessibilityService.getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> otherList = nodeInfo.findAccessibilityNodeInfosByText("领取红包");
            List<AccessibilityNodeInfo> myList = nodeInfo.findAccessibilityNodeInfosByText("查看红包");
            List<AccessibilityNodeInfo> totalList = new ArrayList<>();
            totalList.addAll(otherList);
            totalList.addAll(myList);
            Log.e(TAG, "显示红包控件:" + totalList.size());
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : totalList) {
                while (!item.isClickable()) {
                    item = item.getParent();
                }
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.e(TAG, "红包控件点击！！");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void openPacket() {
        boolean isOpen = false;
        for (int i = 0; i < 50; i++) {
            AccessibilityNodeInfo nodeInfo = myAccessibilityService.getRootInActiveWindow();
            if (nodeInfo != null) {
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/c4q");
                Log.e(TAG, "抢红包按钮:" + list.size());
                for (AccessibilityNodeInfo item : list) {
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.e(TAG, "抢红包按钮点击！！");
                    isOpen = true;
                    break;
                }
                if (list.size() == 0) {
                    List<AccessibilityNodeInfo> lateList = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/c2e");
                    Log.e(TAG, "来晚了，关闭红包按钮:" + lateList.size());
                    for (AccessibilityNodeInfo item : lateList) {
                        item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.e(TAG, "关闭红包按钮点击！！");
                        isOpen = true;
                        break;
                    }
                }

                if (isOpen) {
                    break;
                }

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void close() {
        Log.e(TAG, "处于红包详情页面！！");
        boolean isClose = false;
        for (int i = 0; i < 50; i++) {
            AccessibilityNodeInfo nodeInfo = myAccessibilityService.getRootInActiveWindow();
            if (nodeInfo != null) {
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/hx");
                Log.e(TAG, "红包详情页面关闭控件数量：" + list.size());
//                nodeInfo.recycle();
                for (AccessibilityNodeInfo item : list) {
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    Log.e(TAG, "关闭红包详情页面！！");
                    isClose = true;
                    break;
                }
                if (isClose) {
                    break;
                }
            }
        }
    }
}
