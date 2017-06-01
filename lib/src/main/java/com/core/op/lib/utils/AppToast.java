package com.core.op.lib.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;


/**
 * @author: zjl
 * @Time:  2017/6/1 15:18
 * @Desc:
 */

public class AppToast {

    public static void show(Context context, String message) {
        make(context, message).show();
    }

    private static Toast make(Context context, String message) {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, @StringRes int message) {
        make(context, context.getString(message)).show();
    }
}
