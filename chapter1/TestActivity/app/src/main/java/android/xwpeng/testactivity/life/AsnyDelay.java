package android.xwpeng.testactivity.life;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.xwpeng.testactivity.R;

import java.lang.ref.WeakReference;

/**
 * 测试contex异步延时后的影响
 * Created by xwpeng on 16-8-24.
 */
public class AsnyDelay {
    private final static String TAG = "AsnyDelay";
    private Activity mActivity;

    public AsnyDelay(@NonNull Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<Activity>(activity);
        mActivity = weakReference.get();
    }

    public void startActivity() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] params) {
                SystemClock.sleep(3000);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mActivity.startActivity(new Intent(mActivity, CcActivity.class));
            }
        }.execute();
    }

    public void createView() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] params) {
                SystemClock.sleep(3000);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                new TextView(mActivity);
            }
        }.execute();
    }

    public void inflat(final ViewGroup viewGroup) {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] params) {
                SystemClock.sleep(3000);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                LayoutInflater.from(mActivity.getApplicationContext()).inflate(R.layout.activity_cc, viewGroup);
            }
        }.execute();
    }

    public void showdialog() {
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] params) {
                SystemClock.sleep(3000);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("show Dialog test");
                if (mActivity.isFinishing()) {
                    Log.e(TAG, "onPostExecute: mActivity.isFinishing()");
                    return;
                }
                builder.show();
            }
        }.execute();
    }

}
