package com.odbpo.fenggou.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import com.core.op.Static;

public class CommonUtils {
    private static Activity currentActivity;

    public static Context getContext() {
        return Static.CONTEXT;
    }


    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    public static String[] getStringArr(int id) {
        return getContext().getResources().getStringArray(id);
    }

    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    public static float getDensity() {
        return getContext().getResources().getDisplayMetrics().density;
    }

    public static int dip2px(float dp) {
        float density = getDensity();
        return (int) (dp * density + 0.5f);
    }

    public static float px2dip(int px) {
        float density = getDensity();
        return px / density;
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }


    public static ColorStateList getColorStateList(int mTabTextColorResId) {
        return getContext().getResources()
                .getColorStateList(mTabTextColorResId);
    }


    public static void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static DisplayMetrics getDisplayInfo() {
        return getContext().getResources().getDisplayMetrics();
    }
}
