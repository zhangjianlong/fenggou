package com.odbpo.fenggou.data.api.transformer;


import com.odbpo.fenggou.data.api.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author: zjl
 * @Time: 2017/6/1 15:13
 * @Desc: 请求线程
 */


public class SchedulerTransformer<T> implements Observable.Transformer<BaseResponse<T>, BaseResponse<T>> {

    @Override
    public Observable<BaseResponse<T>> call(Observable<BaseResponse<T>> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SchedulerTransformer<T> create() {
        return new SchedulerTransformer<T>();
    }
}
