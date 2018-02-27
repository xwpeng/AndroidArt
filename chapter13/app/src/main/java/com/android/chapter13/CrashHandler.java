package com.android.chapter13;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.os.Process.killProcess;
import static android.os.Process.myPid;

/**
 * Created by Administrator on 2018/2/25.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = CrashHandler.class.getSimpleName();
    private static final boolean DEBUG = true;
    private static  String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrasshHandler/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {

    }

    public static CrashHandler getsInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
        PATH = mContext.getExternalCacheDir() + "/CrasshHandler/log/";
    }

    @Override
    public void uncaughtException(final Thread thread, Throwable throwable) {
        try {
            dumpExceptionToSDCard(throwable);
            uploadExceptionToServer();
            throwable.printStackTrace();
//            if (mDefaultCrashHandler != null) mDefaultCrashHandler.uncaughtException(thread, throwable);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(mContext, "程序异常，即将推出", Toast.LENGTH_SHORT).show();
                }
            }).start();



            Thread.sleep(3000);
            killProcess(myPid());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将异常信息保存到SD卡中
     */
    private void dumpExceptionToSDCard(Throwable ex) {
        //如果SD卡不存在或无法使用，则把异常信息写入SD卡中
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) Log.w(TAG, "scard unmounted,skip dump exception");
            return;
        }
        File dir = new File(PATH);
        if (!dir.exists()) {
            boolean a = dir.mkdirs();
            Log.e(TAG, "dumpExceptionToSDCard: "+ a);
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.CHINA).format(current);
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        try {
            if (!file.exists()) file.createNewFile();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将信息保存到手机
     */
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);

        //Android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print('_');
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model： ");
        pw.println(Build.MODEL);

        //cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? Build.SUPPORTED_ABIS[0] : Build.CPU_ABI2);
    }

    private void uploadExceptionToServer() {
        //TODO Upload Exception Message to server;
    }

}
