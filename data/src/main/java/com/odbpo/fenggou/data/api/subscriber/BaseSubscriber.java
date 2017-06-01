package com.odbpo.fenggou.data.api.subscriber;

import android.content.Context;

import rx.Subscriber;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:14
 * @Desc: 
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;

    public BaseSubscriber(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}
