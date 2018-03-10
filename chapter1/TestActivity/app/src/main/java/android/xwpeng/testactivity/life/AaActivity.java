package android.xwpeng.testactivity.life;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.xwpeng.testactivity.R;

/**
 * Created by xwpeng on 16-8-21.
 * 测试Activity生命周期
 */
public class AaActivity extends BasicActivity implements View.OnClickListener {
    private static final String TAG = AaActivity.class.getSimpleName();

    //测试全局变量在Activity销毁重建是否恢复
    private int mInt = 1;
    private String mString = "我还在！！！";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa);
        if (savedInstanceState != null) {
            String xwpengMail = savedInstanceState.getString("xwpeng");
            if (!TextUtils.isEmpty(xwpengMail))
                Toast.makeText(this, xwpengMail, Toast.LENGTH_SHORT).show();
        }
        inintView();
    }

    /**
     * 屏幕方向参数：
     ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED,//未指定，此为默认值。由Android系统自己选择合适的方向。
     ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,//横屏
     ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,//竖屏
     ActivityInfo.SCREEN_ORIENTATION_USER,//用户当前的首选方向
     ActivityInfo.SCREEN_ORIENTATION_BEHIND,//继承Activity堆栈中当前Activity下面的那个Activity的方向
     ActivityInfo.SCREEN_ORIENTATION_SENSOR,//由物理感应器决定显示方向
     ActivityInfo.SCREEN_ORIENTATION_NOSENSOR,//忽略物理感应器——即显示方向与物理感应器无关
     ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE,
     ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT,
     ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE,
     ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT,
     ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR,
     */
    private void inintView() {
        findViewById(R.id.main_gob_button).setOnClickListener(this);
        findViewById(R.id.main_inflater_button).setOnClickListener(this);
        findViewById(R.id.main_show_dialog).setOnClickListener(this);
        //force screen rotate
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(this, "onConfigurationChanged", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        SystemClock.sleep(3000);
    }

    @Override
    protected void onStart() {
//        SystemClock.sleep(3000);
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        SystemClock.sleep(3000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("xwpeng", "cocpl0101001@gmail.com");
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //savedInstanceState 不会为空
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        String xwpengMail = savedInstanceState.getString("xwpeng");
        if (!TextUtils.isEmpty(xwpengMail)) Toast.makeText(this, xwpengMail, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "mInt: " + mInt, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "mString: " + mString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_gob_button:
                startActivity(new Intent(AaActivity.this, CcActivity.class));
                break;
            case R.id.main_inflater_button:
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_cc, null);
                ((FrameLayout) findViewById(R.id.frame_c)).addView(view);
                break;
            case R.id.main_show_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(AaActivity.this);
                builder.setTitle("测试弹出dialog后Activity的状态");
                builder.show();
                break;
        }
    }
}
