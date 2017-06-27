package com.core.op.lib.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.core.op.Static;


/**
 * @author: zjl
 * @Time: 2017/6/1 15:18
 * @Desc:
 */

public class AppToast {

    public static void show(String message) {
        make(Static.CONTEXT, message).show();
    }

    private static Toast make(Context context, String message) {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int message) {
        make(Static.CONTEXT, Static.CONTEXT.getString(message)).show();
    }
}
