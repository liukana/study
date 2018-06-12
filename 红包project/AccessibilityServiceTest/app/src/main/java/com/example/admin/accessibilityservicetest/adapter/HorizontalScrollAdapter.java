package com.example.admin.accessibilityservicetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.admin.accessibilityservicetest.findmore.FindMoreViewHolder;
import com.example.admin.accessibilityservicetest.findmore.NormalViewHolder;
import com.example.admin.accessibilityservicetest.R;
import com.example.admin.accessibilityservicetest.util.DensityUtil;
import com.example.admin.accessibilityservicetest.widget.TouchEventDealSelfRecyclerView;

import java.util.ArrayList;

import static com.example.admin.accessibilityservicetest.findmore.FindMoreViewHolder.DEFAULT_WIDTH;


/**
 * Created by liukan on 2018/4/11.
 */

public class HorizontalScrollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TouchEventDealSelfRecyclerView.OnLastItemVisibleListener {

    private Context mContext;
    private LayoutInflater mInflater;

    private boolean hasFindMoreView = false;
    private FindMoreViewHolder findMoreViewHolder = null;
    private final ArrayList<Integer> mList = new ArrayList<>();

    public static final int FIND_MORE = 0;// 发现更多

    public HorizontalScrollAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData() {
        synchronized (mList) {
            mList.clear();
            mList.add(1);
            if (isHasFindMoreView()) {
                mList.add(FIND_MORE);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        synchronized (mList) {
            if (position >= 0 && position < mList.size()) {
                return mList.get(position);
            }
        }
        return -1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == FIND_MORE) {
            itemView = mInflater.inflate(R.layout.item_theme_find_more, parent, false);
            viewHolder = new FindMoreViewHolder(itemView);
            findMoreViewHolder = (FindMoreViewHolder) viewHolder;
        }else{
            itemView = mInflater.inflate(R.layout.item_normal, parent, false);
            viewHolder = new NormalViewHolder(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void addFindMoreView(RecyclerView recyclerView) {
        if (recyclerView instanceof TouchEventDealSelfRecyclerView) {
            ((TouchEventDealSelfRecyclerView) recyclerView).setOnLastItemVisibleListener(this);
        }
        hasFindMoreView = true;
    }

    public void noFindMoreView(RecyclerView recyclerView) {
        if (recyclerView instanceof TouchEventDealSelfRecyclerView) {
            ((TouchEventDealSelfRecyclerView) recyclerView).setOnLastItemVisibleListener(null);
        }
        hasFindMoreView = false;
    }

    public boolean isHasFindMoreView() {
        return hasFindMoreView;
    }


    @Override
    public void onLastItemVisible(MotionEvent ev, int itemRightMarginScreen, int lastitemRightMarginScreen) {
        if (findMoreViewHolder == null) {
            return;
        }
        int distance = 0;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findMoreViewHolder.llFooterRoot.getLayoutParams();
        if (itemRightMarginScreen > DEFAULT_WIDTH) {
            params.width += (itemRightMarginScreen - lastitemRightMarginScreen);
            distance = itemRightMarginScreen - DEFAULT_WIDTH;
        } else {
            params.width = DEFAULT_WIDTH;
            distance = 0;
        }
        findMoreViewHolder.llFooterRoot.setLayoutParams(params);
        findMoreViewHolder.llFooterRoot.setMoveDistance(distance);
        findMoreViewHolder.llFooterRoot.invalidate();

        if (params.width > DensityUtil.dp2px(45)) {
            findMoreViewHolder.tvMore.setText("松开查看");
            if (ev.getAction() == MotionEvent.ACTION_UP) {
                findMoreViewHolder.llFooterRoot.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findMoreViewHolder.llFooterRoot.setMoveDistance(0);
                        findMoreViewHolder.llFooterRoot.invalidate();
                    }
                }, 500);
            }
        } else {
            findMoreViewHolder.tvMore.setText("查看更多");
        }
    }
}
