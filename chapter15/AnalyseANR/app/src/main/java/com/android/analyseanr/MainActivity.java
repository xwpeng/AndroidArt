package com.android.analyseanr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.main_button);
        mButton.setOnClickListener(this);
//        try {
//            Thread.sleep(30 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                testANR();
            }
        }, "main_testanr_thread").start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button:
//                SystemClock.sleep(30 * 1000);
                initView();
                break;
        }
    }

    private synchronized void testANR() {
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void initView() {
        mButton.setText("initedView");
    }
}
