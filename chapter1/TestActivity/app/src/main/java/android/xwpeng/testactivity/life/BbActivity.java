package android.xwpeng.testactivity.life;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.xwpeng.testactivity.R;

/**
 * 透明主题的Activty
 * Created by xwpeng on 16-8-21.
 */
public class BbActivity extends BasicActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb);
        findViewById(R.id.b_toc_button).setOnClickListener(this);
        findViewById(R.id.b_createview_button).setOnClickListener(this);
        findViewById(R.id.b_show_dialog_button).setOnClickListener(this);
        findViewById(R.id.b_inflater_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_toc_button:
                new AsnyDelay(BbActivity.this).startActivity();
                break;
            case R.id.b_createview_button:
                new AsnyDelay(BbActivity.this).createView();
                break;
            case R.id.b_show_dialog_button:
                new AsnyDelay(BbActivity.this).showdialog();
                break;
            case R.id.b_inflater_button:
                new AsnyDelay(BbActivity.this).inflat((FrameLayout)findViewById(R.id.b_frame_c));
                break;
        }
    }
}


