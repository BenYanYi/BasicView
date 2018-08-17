package com.mylove.basicview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author yanyi
 * @date 2018/8/17 15:34
 * @email ben@yanyi.red
 * @overview
 */
public class MainActivity extends AppCompatActivity {
    private CircleProgress circleProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleProgress = findViewById(R.id.circle);
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true){
                        handler.sendEmptyMessage(0x100);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int random = (int) (Math.random() * 100);
            Log.v("", random + "");
            circleProgress.setPower(random, null);
        }
    };
}
