package com.example.admin.accessibilityservicetest.findmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.accessibilityservicetest.R;
import com.example.admin.accessibilityservicetest.util.DensityUtil;
import com.example.admin.accessibilityservicetest.widget.FindMoreView;

/**
 * 查看更多
 * Created by liukan on 2018/4/11.
 */

public class FindMoreViewHolder extends RecyclerView.ViewHolder {

    public FindMoreView llFooterRoot;
    public TextView tvMore;
    public RelativeLayout rlRootView;
    public static final int MAX_WIDTH = DensityUtil.dp2px(50);
    public static final int DEFAULT_WIDTH = DensityUtil.dp2px(36);

    public FindMoreViewHolder(View itemView) {
        super(itemView);
        llFooterRoot = itemView.findViewById(R.id.llFooterRoot);
        tvMore = itemView.findViewById(R.id.tvMore);
        rlRootView = itemView.findViewById(R.id.rlRootView);
    }
}
