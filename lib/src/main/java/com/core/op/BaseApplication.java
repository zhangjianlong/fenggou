package com.core.op;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.view.LayoutInflater;

import com.core.op.lib.AppException;
import com.core.op.lib.BuildConfig;

import timber.log.Timber;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/10
 */
public class BaseApplication extends android.support.multidex.MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Static.CONTEXT = this;
        Static.INFLATER = LayoutInflater.from(this);
        Thread.setDefaultUncaughtExceptionHandler(AppException
                .getAppExceptionHandler(this));
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
