package com.odbpo.fenggou.global;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.core.op.BaseApplication;
import com.core.op.lib.AppException;
import com.facebook.stetho.Stetho;
import com.odbpo.fenggou.di.components.AppComponent;
import com.odbpo.fenggou.di.components.DaggerAppComponent;
import com.odbpo.fenggou.di.modules.AppModule;
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
        Logger.init("YTP")                 // default PRETTYLOGGER or use just init()
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

//                Intent intentLoginActivity = new Intent(CommonUtils.getContext(), LoginActivity.class);
//                CommonUtils.getContext().startActivity(intentLoginActivity);

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
