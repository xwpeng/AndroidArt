package com.android.backstack1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        findViewById(R.id.b_go_c_button).setOnClickListener(this);
        findViewById(R.id.b_go_d_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_go_c_button:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.C");
                startActivity(intent);
                break;
            case R.id.b_go_d_button:
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.D");
                startActivity(intent2);
                break;
        }
    }
}

