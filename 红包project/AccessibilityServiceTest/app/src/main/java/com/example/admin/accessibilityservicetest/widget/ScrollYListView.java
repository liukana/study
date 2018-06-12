package com.example.admin.accessibilityservicetest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by liukan on 2018/4/8.
 */

public class ScrollYListView extends ListView {

    private SparseArray recordSp = new SparseArray(0);//设置容器大小，默认是10
    private int mCurrentfirstVisibleItem;
    private OnScrollYListener onScrollYListener;

    public ScrollYListView(Context context) {
        super(context);
        init();
    }

    public ScrollYListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollYListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setOnScrollYListener(OnScrollYListener onScrollYListener) {
        this.onScrollYListener = onScrollYListener;
    }

    public void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mCurrentfirstVisibleItem = firstVisibleItem;
                View firstView = view.getChildAt(0);//获取当前最顶部的item
                if (firstView != null) {
                    ItemRecod itemRecord = (ItemRecod) recordSp.get(firstVisibleItem);
                    if (itemRecord == null) {
                        itemRecord = new ItemRecod();
                    }
                    //关于这个height和top到底是多少,怎么变化,我们可以通过日志来看,一目了然
                    itemRecord.height = firstView.getHeight();//获取当前最顶部Item的高度
                    itemRecord.top = firstView.getTop();//获取对应item距离顶部的距离
                    recordSp.append(firstVisibleItem, itemRecord);//添加键值对设置值
                    int scrollY = getScrollYDistance();//这就是滑动的距离,我们可以根据这个距离做很多事
                    if (onScrollYListener != null) {
                        onScrollYListener.onScrollY(view, scrollY);
                    }
                }
            }
        });
    }

    private int getScrollYDistance() {
        int height = 0;
        for (int i = 0; i < mCurrentfirstVisibleItem; i++) {
            ItemRecod itemRecod = (ItemRecod) recordSp.get(i);
            if (itemRecod != null) {
                height += itemRecod.height;
            }
        }
        ItemRecod itemRecod = (ItemRecod) recordSp.get(mCurrentfirstVisibleItem);
        if (null == itemRecod) {
            itemRecod = new ItemRecod();
        }
        return height - itemRecod.top;
    }

    public static class ItemRecod {
        int height = 0;
        int top = 0;
    }

    public interface OnScrollYListener {
         void onScrollY(AbsListView view, int scrollY);
    }
}
