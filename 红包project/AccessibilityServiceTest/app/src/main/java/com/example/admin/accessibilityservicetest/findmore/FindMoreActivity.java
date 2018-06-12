package com.example.admin.accessibilityservicetest.findmore;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.admin.accessibilityservicetest.R;
import com.example.admin.accessibilityservicetest.adapter.HorizontalScrollAdapter;
import com.example.admin.accessibilityservicetest.util.DensityUtil;
import com.example.admin.accessibilityservicetest.widget.TouchEventDealSelfRecyclerView;

/**
 * SimpleOnGestureListener 只在activity中生效！！！
 */
public class FindMoreActivity extends AppCompatActivity {

    private TouchEventDealSelfRecyclerView recyclerView;
    private HorizontalScrollAdapter adapter;

    private GestureDetector detector;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DensityUtil.init(this);
        context = this;
        recyclerView = findViewById(R.id.rvList);
        adapter = new HorizontalScrollAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter.addFindMoreView(recyclerView);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setData();
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(context, "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(context, "onDoubleTap", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });
    }
}
