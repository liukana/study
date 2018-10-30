package com.example.admin.accessibilityservicetest.density;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.accessibilityservicetest.MainActivity;
import com.example.admin.accessibilityservicetest.R;

public class DensityTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density_test);
        Toast.makeText(this,"测试toast",Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setTitle("测试dialog")
                .setNegativeButton("cancel",null)
                .setPositiveButton("confirm",null)
                .create().show();
    }
}
