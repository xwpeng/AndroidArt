package android.xwpeng.testactivity.flags;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.xwpeng.testactivity.R;
import android.xwpeng.testactivity.startmode.StandardActivity;

/**
 * 测试标准模式加上clear_top
 */
public class FlagActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_a);
        findViewById(R.id.flag_cleartop_standard).setOnClickListener(this);
        findViewById(R.id.flag_launch_self).setOnClickListener(this);
        findViewById(R.id.flag_exclude_from).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flag_cleartop_standard: {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(this, StandardActivity.class);
                startActivity(intent);
                startActivity(new Intent(this, FlagActivity.class));
            }
            break;
            case R.id.flag_launch_self:
                startActivity(new Intent(this, FlagActivity.class));
                break;
            case R.id.flag_exclude_from: {
                Intent intent = new Intent();
                intent.setClass(this, StandardActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
                break;
            }
        }
    }
}
