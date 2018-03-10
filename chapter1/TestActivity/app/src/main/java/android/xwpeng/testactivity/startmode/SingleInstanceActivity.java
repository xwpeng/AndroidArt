package android.xwpeng.testactivity.startmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.xwpeng.testactivity.R;

/**
 * Created by xwpeng on 16-8-28.
 */
public class SingleInstanceActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "SingleInstanceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        findViewById(R.id.standard_stand_mode).setOnClickListener(this);
        findViewById(R.id.standard_singletop_mode).setOnClickListener(this);
        findViewById(R.id.standard_singletask_mode).setOnClickListener(this);
        findViewById(R.id.standard_single_instance_mode).setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent: ");
    }

    @Override
    public void onClick(View v) {
        Helper.start(this, v);
    }
}
