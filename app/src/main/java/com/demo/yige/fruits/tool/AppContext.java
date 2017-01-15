package com.demo.yige.fruits.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by yige on 2016/12/27.
 */

public class AppContext extends Application implements Thread.UncaughtExceptionHandler {
    public static AppContext mInstance = null;
    private ImageLoaderKit imageLodaer;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        imageLodaer = new ImageLoaderKit(mInstance, null);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
