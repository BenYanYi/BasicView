package com.mylove.basicview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mylove.basicview.progress.CircleView;

/**
 * @author yanyi
 * @date 2018/8/17 15:34
 * @email ben@yanyi.red
 * @overview
 */
public class MainActivity extends AppCompatActivity {
    private CircleView circleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = findViewById(R.id.circle);
        circleView.setRightMsg("%").isDecimal(false);
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        mHandler.sendEmptyMessage(0x100);
                        sleep(10000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int random = (int) (Math.random() * 100);
            circleView.setSize(random);
        }
    };
}
