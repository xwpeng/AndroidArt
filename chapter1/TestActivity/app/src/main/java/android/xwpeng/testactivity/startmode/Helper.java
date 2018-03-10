package android.xwpeng.testactivity.startmode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.xwpeng.testactivity.R;

/**
 * 启动模式测试帮助类
 * Created by xwpeng on 16-8-29.
 */
public class Helper {
    public static void start(Activity activity, View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.standard_stand_mode:
                startUseApplication(activity.getApplication());
                return;
//                intent.setClass(activity, StandardActivity.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                break;
            case R.id.standard_singletop_mode:
                intent.setClass(activity, SingleTopActivity.class);
                break;
            case R.id.standard_singletask_mode:
                intent.setClass(activity, SingleTaskActivity.class);
                break;
            case R.id.standard_single_instance_mode:
                intent.setClass(activity, SingleInstanceActivity.class);
                break;
        }
        activity.startActivity(intent);
    }

    private static void startUseApplication(Context context){
        Intent intent = new Intent(context, StandardActivity.class);
//        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
