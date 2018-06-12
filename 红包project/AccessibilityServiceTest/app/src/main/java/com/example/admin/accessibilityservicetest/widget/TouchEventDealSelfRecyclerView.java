package com.example.admin.accessibilityservicetest.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.admin.accessibilityservicetest.util.DensityUtil;


/**
 * Created by liukan on 2018/4/11.
 */

public class TouchEventDealSelfRecyclerView extends RecyclerView {
    private int lastVisibleItemPosition;
    private OnLastItemVisibleListener onLastItemVisibleListener;
    private int itemRightMarginScreen = 0;
    private int lastitemRightMarginScreen = 0;

    private float mLastX;
    private float mLastY;

    private boolean disallowInterceptTouchEvent = false;

    public TouchEventDealSelfRecyclerView(Context context) {
        super(context);
        init();
    }

    public TouchEventDealSelfRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchEventDealSelfRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setOnLastItemVisibleListener(OnLastItemVisibleListener onLastItemVisibleListener) {
        this.onLastItemVisibleListener = onLastItemVisibleListener;
    }

    private void init() {
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - mLastX;
                float dy = event.getY() - mLastY;
                if (Math.abs(dx) > Math.abs(dy) || disallowInterceptTouchEvent) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    disallowInterceptTouchEvent = true;
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                disallowInterceptTouchEvent = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (lastVisibleItemPosition == getAdapter().getItemCount() - 1 && onLastItemVisibleListener != null) {
            View view = ((LinearLayoutManager) getLayoutManager()).findViewByPosition(getAdapter().getItemCount() - 2);
            itemRightMarginScreen = DensityUtil.getScreenWidth() - view.getRight();
            if (lastitemRightMarginScreen != 0) {
                onLastItemVisibleListener.onLastItemVisible(e, itemRightMarginScreen, lastitemRightMarginScreen);
            }
            lastitemRightMarginScreen = itemRightMarginScreen;
        }
        return super.onTouchEvent(e);
    }

    public interface OnLastItemVisibleListener {
        void onLastItemVisible(MotionEvent ev, int moveX, int lastMoveX);
    }
}
