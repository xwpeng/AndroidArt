package android.xwpeng.testactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.xwpeng.testactivity.flags.FlagActivity;
import android.xwpeng.testactivity.life.AaActivity;
import android.xwpeng.testactivity.startmode.StandardActivity;


/**
 * Created by xwpeng on 16-8-26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_life_resetup_context_button).setOnClickListener(this);
        findViewById(R.id.main_intentfilter_button).setOnClickListener(this);
        findViewById(R.id.main_startmode_button).setOnClickListener(this);
        findViewById(R.id.main_flag_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_life_resetup_context_button:
                startActivity(new Intent(MainActivity.this, AaActivity.class));
                break;
            case R.id.main_intentfilter_button:
                testIntent();
                break;
            case R.id.main_startmode_button:
                startActivity(new Intent(MainActivity.this, StandardActivity.class));
                break;
            case R.id.main_flag_button:
                startActivity(new Intent(MainActivity.this, FlagActivity.class));
                break;
        }

    }

    private void testIntent() {

        Intent intent = new Intent();
//        intent.resolveActivity
        intent.setAction("com.xwpeng.action1");
//                intent.addCategory("android.intent.category.BROWSABLE");
//                intent.setData(Uri.parse("file://aim.png"));
        intent.setType("image/png");
//                intent.setDataAndType(Uri.parse("file://aim.png"), "image/png");
//                intent.setData(Uri.parse("xwpeng://aim"));
               /* Uri uri = new Uri.Builder().path("ssss").build();
                intent.setDataAndType(uri, "text/plain");*/
        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
        else Log.e("xwpeng12: MainActivity", "testIntent: no activity fit intent");
    }
}
