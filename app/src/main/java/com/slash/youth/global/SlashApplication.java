package com.slash.youth.global;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.view.Window;
import android.view.WindowManager;

import com.pingplusplus.android.PingppLog;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.gen.DaoMaster;
import com.slash.youth.gen.DaoSession;
import com.slash.youth.ui.activity.DemandDetailActivity;
import com.slash.youth.ui.activity.GuidActivity;
import com.slash.youth.ui.activity.LoginActivity;
import com.slash.youth.ui.activity.ServiceDetailActivity;
import com.slash.youth.utils.ActivityUtils;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.DistanceUtils;
import com.slash.youth.utils.LogKit;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;
import io.rong.imlib.RongIMClient;


/**
 * Created by zhouyifeng on 2016/8/31.
 */
public class SlashApplication extends android.support.multidex.MultiDexApplication {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;
    private static Application application;

    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static SlashApplication instances;

    static {
        //友盟社会化组件三方平台相关AppKey
        PlatformConfig.setWeixin(GlobalConstants.ThirdAppId.APPID_WECHAT, GlobalConstants.ThirdAppId.AppSecret_WECHAT);
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone(GlobalConstants.ThirdAppId.APPID_QQ, GlobalConstants.ThirdAppId.APPKEY_QQ);
    }

    private static double currentLatitude;
    private static double currentLongitude;
    private DaoMaster.DevOpenHelper mHelper;

    public ArrayList<Activity> listActivities = new ArrayList<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();

        //设置数据库
        instances = this;
        setDatabase();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                LogKit.v("onActivityCreated");
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止Activity横屏
                if (activity instanceof LoginActivity) {
                    activity.requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题
                    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }

                listActivities.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                LogKit.v("onActivityStarted");
                ActivityUtils.currentActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                LogKit.v("onActivityResumed");
                MobclickAgent.onResume(activity);

                if (activity instanceof GuidActivity) {
                    MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.CLICK_PAGE_ONE);
                } else if (activity instanceof DemandDetailActivity) {
                    MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.IDLE_TIME_CLICK_REQUIREMENT_DETAIL);
                } else if (activity instanceof ServiceDetailActivity) {
                    MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.IDLE_TIME_CLICK_SERVICE_DETAIL);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogKit.v("onActivityPaused");
                MobclickAgent.onPause(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                LogKit.v("onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                LogKit.v("onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogKit.v("onActivityDestroyed");

                listActivities.remove(activity);
            }
        });

        UMShareAPI.get(this);
        Config.REDIRECT_URL = "www.slashyouth.com";
        Config.dialogSwitch = false;//不使用默认dialog

        context = getApplicationContext();
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
        application = this;
        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG);
        PingppLog.DEBUG = true;
//        x.image().clearCacheFiles();
//        x.image().clearMemCache();
        //注册微信的APPID

        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIMClient.init(this);
        }

        LogKit.v("one");
        //注册融云消息接收监听器
        MsgManager.setMessReceiver();
        //通过token连接融云
        //10000的token
//        MsgManager.connectRongCloud("wjTdt2YAMEl+kgwlIqEc9lpJh/sDSWvAD7s9SQ9t2IpAr12GGUolcoB06J/c93lRr1B3q/o8EjamBqhw9iQHqA==");

        //10002的token
//        MsgManager.connectRongCloud("OWellbpuoVve83MyzB06YVpJh/sDSWvAD7s9SQ9t2IpAr12GGUolcieJmtTlGLY0xSEqae+jIN8rMYEU2oRTbQ==");

        //10003的tokenF
//        MsgManager.connectRongCloud("J+NjV3RqnU1xfMolWAd6T1pJh/sDSWvAD7s9SQ9t2IpAr12GGUolclxn65zIYrLNwE/geOWQulCDALq54CXhkA==");
        LogKit.v("two");


        //定位一次，获取当前所在地的经纬度
        DistanceUtils distanceUtils = new DistanceUtils();
        distanceUtils.getLatAndLng(this);
//        currentLatitude = distanceUtils.currentLatitude;
//        currentLongitude = distanceUtils.currentLongitude;

//        LogKit.v("currentLatitude:" + currentLatitude);
//        LogKit.v("currentLongitude:" + currentLongitude);

        //初始化GalleryFinal
//        ThemeConfig theme = ThemeConfig.CYAN;
        ThemeConfig theme = new ThemeConfig.Builder().setTitleBarTextColor(0xffffffff).setTitleBarBgColor(0xff31C5E4).setTitleBarIconColor(0xffffffff).build();
        ImageLoader imageloader = new XUtilsImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(context, imageloader, theme).build();
        GalleryFinal.init(coreConfig);

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        LogKit.v("heapinfo--maxMemory:" + maxMemory / (1024 * 1024));

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();
        LogKit.v("heapinfo--activityManager.getLargeMemoryClass():" + largeMemoryClass);
        LogKit.v("heapinfo--activityManager.getMemoryClass():" + memoryClass);
    }


    public static SlashApplication getInstances() {
        return instances;
    }

    public class XUtilsImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

        private Bitmap.Config mImageConfig;

        public XUtilsImageLoader() {
            this(Bitmap.Config.RGB_565);
        }

        public XUtilsImageLoader(Bitmap.Config config) {
            this.mImageConfig = config;
        }

        @Override
        public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            ImageOptions options = new ImageOptions.Builder()
                    .setLoadingDrawable(defaultDrawable)
                    .setFailureDrawable(defaultDrawable)
                    .setConfig(mImageConfig)
                    .setSize(width, height)
                    .setCrop(true)
                    .setUseMemCache(false)
                    .build();
            x.image().bind(imageView, "file://" + path, options);

        }

        @Override
        public void clearMemoryCache() {
        }
    }


    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Application getApplication() {
        return application;
    }


    public static void setCurrentLongitude(double currentLongitude) {
        SlashApplication.currentLongitude = currentLongitude;
    }

    public static double getCurrentLatitude() {
        return currentLatitude;
    }

    public static void setCurrentLatitude(double currentLatitude) {
        SlashApplication.currentLatitude = currentLatitude;
    }

    public static double getCurrentLongitude() {
        return currentLongitude;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        mHelper = new DaoMaster.DevOpenHelper(this.getApplicationContext(), "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
