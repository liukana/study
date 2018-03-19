package com.example.admin.accessibilityservicetest.Service;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.example.admin.accessibilityservicetest.ApplicationForRedEnvelope;


/**
 * Created by liukan on 2018/3/9.
 */

public class MyAccessibilityService extends AccessibilityService {
    public static final String TAG = MyAccessibilityService.class.getSimpleName();
    private ApplicationForRedEnvelope applicationForRedEnvelope;
    private String curruntClassName = "";

    /**
     * 有关AccessibilityEvent事件的回调函数.系统通过sendAccessibiliyEvent()不断的发送AccessibilityEvent到此处
     *
     * @param event
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                applicationForRedEnvelope.handleNotification(event);
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                String className = event.getClassName().toString();
                Log.e(TAG,className);
                if(className.startsWith("com.tencent.mm")){
                    curruntClassName = className;
                }
                if (curruntClassName.equals("com.tencent.mm.ui.LauncherUI")) {
                    applicationForRedEnvelope.goToChattingPage();
                } else if (curruntClassName.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI") || curruntClassName.equals("com.alibaba.android.dingtalk.redpackets.activities.FestivalRedPacketsPickActivity")) {
                    applicationForRedEnvelope.openPacket();
                } else if (curruntClassName.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI") || curruntClassName.equals("com.alibaba.android.dingtalk.redpackets.activities.RedPacketsDetailActivity")) {
                    applicationForRedEnvelope.close();
                }

                break;
        }

    }

    @Override
    public void onInterrupt() {

    }

    /**
     * 系统成功绑定该服务时被触发,也就是当你在设置中开启相应的服务,系统成功的绑定了该服务时会触发,通常我们可以在这里做一些初始化操作
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "系统成功绑定该服务");
        applicationForRedEnvelope = ApplicationForRedEnvelope.getInstance();
        applicationForRedEnvelope.setMyAccessibilityService(this);
    }

    public void setApplication(ApplicationForRedEnvelope application){
        applicationForRedEnvelope = application;
    }
    /**
     * 递归查找当前聊天窗口中的红包信息
     * <p>
     * 聊天窗口中的红包都存在"领取红包"一词,因此可根据该词查找红包
     *
     * @param node
     */
//    public AccessibilityNodeInfo recycle(AccessibilityNodeInfo node) {
//        if (node.getChildCount() == 0) {
//            if (node.getText() != null) {
//                if ("领取红包".equals(node.getText().toString())) {
//                    return node;
//                }
//            }
//        } else {
//            for (int i = 0; i < node.getChildCount(); i++) {
//                if (node.getChild(i) != null) {
//                    recycle(node.getChild(i));
//                }
//            }
//        }
//        return node;
//    }


}
