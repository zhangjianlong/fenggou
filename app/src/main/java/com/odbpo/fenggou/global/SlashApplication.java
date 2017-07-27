package com.odbpo.fenggou.global;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.core.op.BaseApplication;
import com.core.op.lib.AppException;
import com.facebook.stetho.Stetho;
import com.odbpo.fenggou.data.UrlRoot;
import com.odbpo.fenggou.di.components.AppComponent;
import com.odbpo.fenggou.di.components.DaggerAppComponent;
import com.odbpo.fenggou.di.modules.AppModule;
import com.odbpo.fenggou.feature.main.MainActivity;
import com.odbpo.fenggou.util.CommonUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;


public class SlashApplication extends BaseApplication {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;

    public static Application application;


    private static double currentLatitude;
    private static double currentLongitude;

    public ArrayList<Activity> listActivities = new ArrayList<Activity>();


    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        application = this;
        UrlRoot.setApiPath();
        Logger.init("ZJL")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);

        AppException exception = AppException.getAppExceptionHandler(this);
        exception.setOnCrashActionListener(new AppException.OnCrashActionListener() {
            @Override
            public void onCrashAction() {
                ArrayList<Activity> listActivities = ((SlashApplication) SlashApplication.getApplication()).listActivities;
                for (Activity activity : listActivities) {
                    if (activity != null) {
                        activity.finish();
                        listActivities.remove(activity);
                        activity = null;
                    }
                }

            }
        });

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止Activity横屏

                listActivities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                listActivities.remove(activity);
            }
        });


        Thread.setDefaultUncaughtExceptionHandler(exception);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


    public static Application getApplication() {
        return application;
    }


}
