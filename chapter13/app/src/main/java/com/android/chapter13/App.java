package com.android.chapter13;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * App类
 * Created by Administrator on 2018/2/25.
 */

public class App extends Application {
    private static final String TAG = Application.class.getSimpleName();
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler crashHandler = CrashHandler.getsInstance();
        crashHandler.init(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    public static App getInstance(){
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
