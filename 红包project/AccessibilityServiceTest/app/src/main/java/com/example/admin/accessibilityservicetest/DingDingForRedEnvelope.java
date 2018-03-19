package com.example.admin.accessibilityservicetest;

import android.app.Instrumentation;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.io.IOException;
import java.util.List;

/**
 * 钉钉红包
 * <p>
 * Created by liukan on 2018/3/9.
 */

public class DingDingForRedEnvelope extends ApplicationForRedEnvelope {
    public static final String TAG = DingDingForRedEnvelope.class.getSimpleName();
    private boolean hasOpen = false;

    @Override
    public void handleNotification(AccessibilityEvent event) {

    }

    @Override
    public void goToChattingPage() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void openPacket() {
//        for (int i = 0; i < 50; i++) {
//            AccessibilityNodeInfo nodeInfo = myAccessibilityService.getRootInActiveWindow();
//            if (nodeInfo != null) {
//                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.alibaba.android.rimet:id/tv_view_detail");
//                for (AccessibilityNodeInfo item : list) {
//                    Log.e(TAG, "数量：" + item.getParent().getChildCount());
//                    for (int j = 0; j < item.getParent().getChildCount(); j++) {
//                        AccessibilityNodeInfo child = item.getParent().getChild(j);
//                        Log.e(TAG, "名称：" + child.getClassName());
//                    }
//                }
//            }
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void close() {
        hasOpen = true;
        Log.e(TAG, "处于钉钉红包详情页面！！");
        boolean isClose = false;
        for (int i = 0; i < 50; i++) {
            AccessibilityNodeInfo nodeInfo = myAccessibilityService.getRootInActiveWindow();
            if (nodeInfo != null) {
                List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("com.alibaba.android.rimet:id/ui_common_base_ui_activity_toolbar");
                Log.e(TAG, "钉钉红包详情页面关闭控件数量：" + list.size());
                nodeInfo.recycle();
                for (AccessibilityNodeInfo item : list) {
                    if (item.getChildCount() > 0) {
                        item = item.getChild(0);
                        item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.e(TAG, "关闭钉钉红包详情页面！！");
                        isClose = true;
                        break;
                    }
                }
                if (isClose) {
                    break;
                }
            }
        }
    }
}
