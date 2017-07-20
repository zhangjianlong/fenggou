package com.core.op.lib.weight.popupWindow;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.Arrays;

/**
 * @author: zjl
 * @Time: 2017/7/19 16:05
 * @Desc:
 */

public class WindowBuilder {
    protected View anchor;

    @LayoutRes
    protected int contentViewId;
    protected Context context;
    protected int aniType = -1;
    protected boolean isCancelable;


    public WindowBuilder setAnchor(View view) {
        this.anchor = view;
        return this;

    }


    public WindowBuilder setCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        return this;

    }

    public WindowBuilder setContentView(int view) {
        this.contentViewId = view;
        return this;

    }


    public WindowBuilder setAniType(int aniType) {
        this.aniType = aniType;
        return this;

    }

    public WindowBuilder(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.context = context;
    }


}
