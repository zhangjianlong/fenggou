package com.odbpo.fenggou.data.api.subscriber;

import android.content.Context;
import android.widget.Toast;

import com.odbpo.fenggou.data.api.subscriber.*;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public abstract class ToastSubscriber<T> extends BaseSubscriber<T> {

    public ToastSubscriber(Context context) {
        super(context);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}