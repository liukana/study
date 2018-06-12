package com.example.admin.accessibilityservicetest.SensorManagerTest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.admin.accessibilityservicetest.R;

import java.time.Duration;
import java.time.LocalTime;


public class SensorActivity extends AppCompatActivity {

    private ImageView iv;

    private SensorManager sensorManager;
    private Sensor mAccelerometer;
    private SensorEventListener listener;

    float lastX, lastY, lastZ;
    LocalTime lastTime;
    float speedX,speedY,speedZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        iv = findViewById(R.id.iv);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (lastX == lastY && lastX == lastZ) {
                    lastX = event.values[0];
                    lastY = event.values[1];
                    lastZ = event.values[2];
                    lastTime = LocalTime.now();
                } else {

                    long duration = Duration.between(lastTime,LocalTime.now()).getSeconds();

                    speedX = (event.values[0] - lastX) * duration;
                    speedY = (event.values[1] - lastY) * duration;
                    speedZ = (event.values[2] - lastZ) * duration;
                    Log.e("lk", "speedX:" + speedX + ",speedY:" + speedY + ",speedZ:" + speedZ);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(listener);
    }
}
