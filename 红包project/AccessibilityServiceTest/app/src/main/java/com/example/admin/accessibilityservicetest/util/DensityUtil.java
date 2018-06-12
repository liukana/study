package com.example.admin.accessibilityservicetest.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

public class DensityUtil {
    private static DisplayMetrics sDisplayMetrics = new DisplayMetrics();
    private static Application sApp;
    private static final String TAG = DensityUtil.class.getSimpleName();

    public static void init(Activity activity) {
        sApp = activity.getApplication();
        ((WindowManager) sApp.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(sDisplayMetrics);
    }

    /**
     * 获取屏幕宽度
     *
     * @return width
     */
    public static int getScreenWidth() {
        return sDisplayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    public static int getScreenHeight() {
        return sDisplayMetrics.heightPixels;
    }

    public static float getDpi() {
        return sDisplayMetrics.densityDpi;
    }

    public static float getDensity() {
        return sDisplayMetrics.density;
    }

    public static float getScaledDensity() {
        return sDisplayMetrics.scaledDensity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                sDisplayMetrics);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = sDisplayMetrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                sDisplayMetrics);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(float pxValue) {
        final float scale = sDisplayMetrics.scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void resetDensity(Application app) {
        sApp = app;
        ((WindowManager) sApp.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(sDisplayMetrics);
        Log.v(TAG, "-- display density=" + sDisplayMetrics.scaledDensity);
    }

    /**
     * @param id:R.dimen.XX
     * @return
     */
    public static int dp2pxUsingDimenId(@DimenRes int id) {
        return (int) Math.ceil(sApp.getApplicationContext()
                .getResources().getDimension(id));
    }
}