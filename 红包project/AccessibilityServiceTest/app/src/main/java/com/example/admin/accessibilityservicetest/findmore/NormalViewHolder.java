package com.example.admin.accessibilityservicetest.findmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.admin.accessibilityservicetest.R;

/**
 * Created by liukan on 2018/4/11.
 */

public class NormalViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout rlRootView;

    private Context context;

    public NormalViewHolder(View itemView) {
        super(itemView);
        rlRootView = itemView.findViewById(R.id.rlRootView);
        context = itemView.getContext();
    }
}
