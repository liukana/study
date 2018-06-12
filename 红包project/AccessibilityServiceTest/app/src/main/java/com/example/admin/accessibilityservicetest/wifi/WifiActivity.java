package com.example.admin.accessibilityservicetest.wifi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.accessibilityservicetest.R;
import com.example.admin.accessibilityservicetest.widget.ScrollYListView;

import java.util.ArrayList;
import java.util.List;

public class WifiActivity extends AppCompatActivity {

    private static Context mContext;
    private static Button btnFindWifi;
    private static ScrollYListView lvWifiInfos;
    private TextView tvTitle;
    private RelativeLayout rlToolBar;
    private View divide;

    private static View headerView;

    private static WifiManager wifiManager;
    private WifiRecevier mWifiRecevier;
    private static List<ScanResult> result = new ArrayList();
    private static WifiAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        mContext = this;
        rlToolBar = findViewById(R.id.rlToolBar);
        divide = findViewById(R.id.divide);
        btnFindWifi = findViewById(R.id.btnFindWifi);
        lvWifiInfos = findViewById(R.id.lvWifiInfos);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setAlpha(0.0f);
        adapter = new WifiAdapter(mContext);
        lvWifiInfos.setAdapter(adapter);
        headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_adapter_wifi_header, null);
        lvWifiInfos.addHeaderView(headerView);
        headerView.setVisibility(View.GONE);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        mWifiRecevier = new WifiRecevier();
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mWifiRecevier, filter);

        btnFindWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permission = new String[2];
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    permission[0] = Manifest.permission.ACCESS_FINE_LOCATION;
                }
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    permission[1] = Manifest.permission.ACCESS_COARSE_LOCATION;
                }
                if (permission[0] != null || permission[1] != null) {
                    Log.d("hello", "regist Permission");
                    requestPermissions(permission, 1);
                }
                wifiManager.startScan();
                Toast.makeText(mContext, "开始扫描!!", Toast.LENGTH_SHORT).show();
            }
        });

        lvWifiInfos.setOnScrollYListener(new ScrollYListView.OnScrollYListener() {
            @Override
            public void onScrollY(AbsListView view, int scrollY) {
                if (scrollY >= dp2px(50f)) {
                    rlToolBar.setBackground(getDrawable(R.color.colorWhite));
                    divide.setVisibility(View.VISIBLE);
                } else {
                    if (scrollY >= dp2px(30f)) {
                        tvTitle.setAlpha((float) ((scrollY - dp2px(30f)) / dp2px(15f)));
                    }else{
                        tvTitle.setAlpha(0.0f);
                    }
                    ((LinearLayout) headerView).getChildAt(0).setAlpha(1.0f - (float) ((scrollY - dp2px(20f)) / dp2px(25f)));
                    rlToolBar.setBackground(null);
                    divide.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public static class WifiRecevier extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case WifiManager.SCAN_RESULTS_AVAILABLE_ACTION:
                    Toast.makeText(mContext, "扫描结果显示!!", Toast.LENGTH_SHORT).show();

                    result = wifiManager.getScanResults();
                    List wifiNames = new ArrayList();
                    if (result != null) {
                        for (int i = 0; i < result.size(); i++) {
//                            if (!wifiNames.contains(result.get(i).SSID)) {
                            wifiNames.add(result.get(i).SSID);
//                            }
                        }
                        adapter.setList(wifiNames);
                    }
                    headerView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                // 权限申请结果回调
                break;
        }
    }

    public double dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (double) (dpValue * scale + 0.5f);
    }

}
