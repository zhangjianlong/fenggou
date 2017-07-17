package com.core.op.bindingadapter.layout;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: zjl
 * @Time: 2017/7/17 16:18
 * @Desc:
 */

public class ViewBindingAdapter {

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) width;
        view.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) height;
        view.setLayoutParams(params);
    }
}
